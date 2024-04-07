folder('Tools') {
    description('Folder for miscellaneous tools.')
}

job('Tools/clone-repository') {
    description('Clones a Git repository to the workspace.')
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the repository to clone')
    }
    scm {
        
    }
    triggers {
        
    }
    steps {
        
        shell('''
          echo Cloning the repository from $GIT_REPOSITORY_URL
          git clone $GIT_REPOSITORY_URL .
        ''')
    }
    wrappers {
        preBuildCleanup() 
    }
}

job('Tools/SEED') {
    description('SEED job to generate jobs from specified parameters.')
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name')
        stringParam('DISPLAY_NAME', '', 'Display name for the job')
    }
    steps {
        /
        dsl {
            external('path/to/your/job_dsl.groovy') 
        }
    }
    
}

def generateJob = { String displayName, String githubName ->
    job("${displayName}") {
        description("Job generated from SEED for ${githubName}")
        scm {
            git("https://github.com/${githubName}.git")
        }
        triggers {
            cron('H/1 * * * *') 
        }
        steps {
            shell('make fclean')
            shell('make')
            shell('make tests_run')
            shell('make clean')
        }
        wrappers {
            preBuildCleanup() 
        }
    }
}
