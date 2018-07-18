import groovy.transform.BaseScript

@GrabResolver(name='_pipeline', root='https://dl.bintray.com/cvarjao/maven')
@Grab(group='ca.bc.gov.devops.cd.pipeline', module='ocp-pipeline', version='0.0.3')
import ca.bc.gov.devops.OpenShiftBuildHelper

@BaseScript ca.bc.gov.devops.BasicBuild _super

@groovy.transform.SourceURI URI sourceURI

runScript(sourceURI)

println 'Done!!'
