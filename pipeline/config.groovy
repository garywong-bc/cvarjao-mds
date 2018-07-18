app {
    name = 'mds'
    version = 'snapshot'
    environments = ["dev", "test", "prod"]

    git {
        workDir = ['git', 'rev-parse', '--show-toplevel'].execute().text.trim()
        uri = ['git', 'config', '--get', 'remote.origin.url'].execute().text.trim()
        commit = ['git', 'rev-parse', 'HEAD'].execute().text.trim()
        ref = ['bash','-c', 'git config branch.`git name-rev --name-only HEAD`.merge'].execute().text.trim()
        changeId = "${opt.'pr'}"
    }

    build {
        name = "pr-${app.git.changeId}"
        prefix = "${app.name}-"
        suffix = "-${app.git.changeId}"
        namespace = 'empr-mds-tools'
        timeoutInSeconds = 60*20 // 20 minutes
        templates = [
                [
                    'file':'openshift/_nodejs.bc.json',
                    'params':[
                        'NAME':"mds-web-api",
                        'SUFFIX': "${app.build.suffix}",
                        'OUTPUT_TAG_NAME':"pr-${app.git.changeId}",
                        'SOURCE_CONTEXT_DIR': "web-api",
                        'SOURCE_REPOSITORY_URL': "${app.git.uri}"
                    ]
                ],
        ]
    }

    deployment {
        name = "${vars.deployment.key}-pr-${app.git.changeId}"
        prefix = "${app.name}-"
        suffix = "-${app.git.changeId}"
        namespace = "${vars.deployment.namespace}"
        timeoutInSeconds = 60*20 // 20 minutes
        templates = [
                [
                    'file':'openshift/_nodejs.dc.json',
                    'params':[
                        'NAME':"mds-web-api",
                        'SUFFIX': "${app.deployment.suffix}",
                        'TAG_NAME':"${app.deployment.name}",
                        'APPLICATION_DOMAIN': "${vars.modules.'web-api'.HOST}"
                    ]
                ]
        ]
    }
}

//Default Values (Should it default to DEV or PROD???)
vars {
    modules {
        'web-api' {
            HOST = "mds-api-${app.git.changeId}-csnr-devops-lab-deploy.pathfinder.gov.bc.ca"
        }
    }
}

environments {
    'dev' {
        vars {
            DB_PVC_SIZE = '1Gi'
            deployment {
                key = 'dev'
                namespace = 'empr-mds-dev'
            }
        }
    }
    'test' {
        vars {
            deployment {
                key = 'test'
                namespace = 'empr-mds-test'
            }
        }
    }
    'prod' {
        vars {
            deployment {
                key = 'prod'
                namespace = 'empr-mds-prod'
            }
        }
    }
}