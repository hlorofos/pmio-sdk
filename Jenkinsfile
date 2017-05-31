#!/groovy

        properties([[$class: 'ParametersDefinitionProperty',
            parameterDefinitions: [
                [$class: 'StringParameterDefinition', defaultValue: '4.0.0.qacore.processmaker.net/api/v1', description: 'URL Path for PMCore API installation', name : 'PMCOREHOST'],
                [$class: 'StringParameterDefinition', defaultValue: 'Default user key', description: 'Auth key for user Test', name : 'KEY_TEST']
            ]
        ]]);

node {

    def actual_key = ''

    // be positive =)
    currentBuild.result = 'SUCCESS'

    checkout scm

    gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()

try {
    stage('Build') {

        sh "./build.sh"

        if (params.KEY_TEST != 'Default user key') {
            sh """
            cd api/1.0.0/php/pmio-sdk-php/
            echo '<?php' >.env
            echo '\$host = "${params.PMCOREHOST}";' >>.env
            echo '\$key["Test"] = "${params.KEY_TEST}";' >>.env

            cat .env
            """
        }

    }

        if ( fileExists ('api/1.0.0/php/pmio-sdk-php/.env')) {

            stage('Functional Test') {
            wrap([$class: 'AnsiColorBuildWrapper']) {

            sh """

                cd api/1.0.0/php/pmio-sdk-php/

                php -v

                if [ ! -f composer.phar ]; then
                wget \"https://getcomposer.org/composer.phar\" -O composer.phar
                fi

                php composer.phar install
                php composer.phar dump-autoload

                vendor/bin/phpunit test/Api --debug --log-junit=junit.xml || true
            """

            junit 'api/1.0.0/php/pmio-sdk-php/junit.xml'

                echo 'Status: ' + currentBuild.result
                    hipchatSend (color: 'GREEN', notify: true, room: 'pm.io', textFormat: false, failOnError: false,
                    message: "<img src='http://ieltsplanet.info/wp-content/uploads/avatars/11860/3135a9543009deaed32574afacdb0c53-bpthumb.png' width=50 height=50 align='left'>$env.JOB_NAME [#${env.BUILD_NUMBER}] - ${currentBuild.result} (<a href='${env.BUILD_URL}'>Open</a>)"
                    )

            }
            }

        }

    if (currentBuild.result == 'SUCCESS') {

        stage('Publishing') {
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'docs/1.0.0/html', reportFiles: 'index.html', reportName: 'API SDK HTML Docs'])
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'docs/1.0.0/html2', reportFiles: 'index.html', reportName: 'API SDK HTML v.2'])
        }

    } else {
            hipchatSend (color: 'YELLOW', notify: true, room: 'pm.io', textFormat: false, failOnError: false,
                  message: "$env.JOB_NAME [#${env.BUILD_NUMBER}] - ${currentBuild.result} (<a href='${env.BUILD_URL}'>Open</a>)"
            )
    }
} catch(error) {
        echo error
    currentBuild.result = "FAILED"
    hipchatSend (color: 'RED', notify: true, room: 'pm.io', textFormat: false, failOnError: false,
        message: "<img src='http://i.istockimg.com/file_thumbview_approve/86219539/3/stock-illustration-86219539-cute-cartoon-piggy.jpg' width=50 height=50 align='left'>$env.JOB_NAME [#${env.BUILD_NUMBER}] - ${currentBuild.result} (<a href='${env.BUILD_URL}'>Open</a>)"
    )
}
}