folder('CI-Pipelines') {
  displayName('CI Pipelines')
  description('CI Pipelines')
}

def component = ["frontend","users","login","todo"];

def count=(component.size()-1)
for (i in 0..count) {
  def j=component[i]
  pipelineJob("CI-Pipelines/${j}-ci") {
    configure { flowdefinition ->
      flowdefinition << delegate.'definition'(class:'org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition',plugin:'workflow-cps') {
        'scm'(class:'hudson.plugins.git.GitSCM',plugin:'git') {
          'userRemoteConfigs' {
            'hudson.plugins.git.UserRemoteConfig' {
              'url'('https://github.com/sireeshazs/'+j+'.git')
            }
          }
          'branches' {
            'hudson.plugins.git.BranchSpec' {
              'name'('*/main')
            }
          }
        }
        'scriptPath'('Jenkinsfile')
        'lightweight'(true)
      }
    }
  }
}

