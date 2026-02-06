# MY MARVIN — Jenkins Configuration as Code CI/CD Instance

MY MARVIN is a Jenkins-based automation project built around **Configuration as Code** and **Job DSL**. It showcases a clean, testable Jenkins setup that can automate anything from small recurring maintenance tasks to full CI/CD production pipelines.

This repository is **evaluated entirely through automated tests**, so the Jenkins instance must be reproducible and deterministic.

---

## Key Principles

* **Jenkins LTS** only (current Long-Term Support release)
* **Minimal plugin set** (only what’s required)
* **Everything as code**

  * All Jenkins configuration in **one** YAML file: `my_marvin.yml`
  * All Job DSL in **one** Groovy file: `job_dsl.groovy`

---

## Project Requirements

### Jenkins

* Uses the **current Jenkins LTS** version.

### Plugins (required)

Only the following plugins should be installed:

* `cloudbees-folder`
* `configuration-as-code`
* `credentials`
* `github`
* `instance-identity`
* `job-dsl`
* `script-security`
* `structs`
* `role-strategy`
* `ws-cleanup`

---

## Configuration Layout

Your Jenkins configuration must be defined through:

### 1) `my_marvin.yml`

A single JCasC file that defines:

#### Global configuration

* **System message**:
  `Welcome to the Chocolatine-Powered Marvin Jenkins Instance.`
* **User sign-up**: Disabled

#### Users

Create the following users (IDs and passwords must match what tests expect):

* Hugo
* Garance
* Jeremy
* Nassim

Passwords must be provided through **environment variables** (see below).

#### Authorization strategy

Use **Role-Based Authorization Strategy** with the following roles:

* `admin`
* `ape`
* `gorilla`
* `assist`

Each role must be assigned the **exact permissions** expected by the project tests.

---

### 2) `job_dsl.groovy`

A single Job DSL file that defines all jobs/folders, including:

#### Folder

* **`Tools`** folder (miscellaneous utility jobs)

#### Job: `clone-repository`

* Clones a given Git repository (parameterized repository URL)

#### Job: `SEED`

* A Job DSL seed job that generates other jobs dynamically based on parameters

#### Generated jobs (via SEED)

Each generated job must run these shell steps (in order):

1. `make fclean`
2. `make`
3. `make tests_run`
4. `make clean`

---

## Repository Structure

At the root of your Jenkins directory (the directory Jenkins reads from), you must place:

```
.
├── my_marvin.yml
└── job_dsl.groovy
```

No additional JCasC or DSL files should be used.

---

## Setup Guide

### 1) Install and run Jenkins LTS

Ensure Jenkins is installed and running on the **LTS** line.

### 2) Install required plugins

Verify the plugin list matches exactly the required set:

* cloudbees-folder
* configuration-as-code
* credentials
* github
* instance-identity
* job-dsl
* script-security
* structs
* role-strategy
* ws-cleanup

### 3) Add JCasC configuration

Place `my_marvin.yml` at the **root of your Jenkins directory**.

Also ensure Jenkins is configured to load it (commonly via the environment variable below):

* `CASC_JENKINS_CONFIG=/path/to/my_marvin.yml`

### 4) Add the Job DSL script

Place `job_dsl.groovy` at the **root of your Jenkins directory**.

### 5) Set environment variables for user passwords

Passwords must not be hardcoded. Export the password environment variables expected by the project tests.

Example:

```bash
export USER_HUGO_PASSWORD="..."
export USER_GARANCE_PASSWORD="..."
export USER_JEREMY_PASSWORD="..."
export USER_NASSIM_PASSWORD="..."
```
---

## What the Tests Validate

Automated tests typically verify:

* Jenkins is running **LTS**
* Only required plugins are installed
* `my_marvin.yml` exists and contains all required Jenkins configuration
* `job_dsl.groovy` exists and defines all required jobs/folders
* Users exist with correct IDs and password sources (environment variables)
* Role-based strategy is enabled with required roles and permissions
* Jobs behave as expected (clone job, seed job, generated job steps)

---

## Notes

* This project is strict by design: **any UI-driven manual changes will not be considered** and may break reproducibility.
* Keep everything declarative, idempotent, and centralized in the two required files.


