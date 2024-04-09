MY_MARVIN Project 


Overview

The MY_MARVIN project is designed to harness the power of Jenkins, an Open Source automation platform, to automate tasks ranging from simple periodic cleanups to full-scale production deployments. At the heart of this project lies the Continuous Integration & Continuous Delivery (CI/CD) pipeline, pivotal for modern DevOps practices.

This project is evaluated entirely through Automated Tests, emphasizing the Configuration as Code (CaC) principle via Jenkins Configuration as Code (JCasC) and the Job Domain Specific Language (DSL) for creating jobs within Jenkins.
Project Requirements

    Jenkins LTS Version: The project utilizes the current Long-Term Support (LTS) version of Jenkins.
    Plugins: Only essential plugins are installed, including cloudbees-folder, configuration-as-code, credentials, github, instance-identity, job-dsl, script-security, structs, role-strategy, and ws-cleanup.
    Configuration as Code (CaC): All Jenkins configurations are defined in a single YAML file named my_marvin.yml.
    Job DSL: All DSL scripts must be centralized in one file named job_dsl.groovy.

Configuration Details
Global Configuration

    System message: "Welcome to the Chocolatine-Powered Marvin Jenkins Instance."
    User sign-up: Disabled.

Users

    Users to be created: Hugo, Garance, Jeremy, Nassim, with specific IDs and passwords set via environment variables.

Authorization Strategy

    Role-based authorization strategy with predefined roles: admin, ape, gorilla, and assist, each with specific permissions.

Jobs

    Tools Folder: Contains miscellaneous tools.
    clone-repository Job: Clones a specified Git repository.
    SEED Job: Dynamically creates jobs based on provided parameters.
    Generated Jobs: Perform a series of shell script steps including make fclean, make, make tests_run, and make clean.

Setup and Configuration Guide

    Environment Setup: Ensure Jenkins is installed and running the LTS version.
    Plugin Installation: Verify that all required plugins are installed.
    Configuration File: Place the my_marvin.yml file at the root of your Jenkins directory.
    Job DSL Script: Place the job_dsl.groovy script at the root of your Jenkins directory.
    Environment Variables: Set the necessary environment variables for user passwords.

