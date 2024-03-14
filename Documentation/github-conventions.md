# Issue Creation and Completion

## How to create an issue ?

1.  Copy issue key from Jira 
2.  Hop on GitHub and go to the issues tab
3.  Click New issue
4.  Title will be the copied issue key from Jira 

> [!NOTE]
> You can also add more to it, if you feel it would help

5.  Add an useful description
6.  If it's your task, than you can assign yourself, otherwise leave it empty
7.  Add a descriptive label for the issue
8.  Click on Submit new issue

## How to start solving an issue ?

1.  Click on the issue
2.  If there is no one assigned, assign yourself
3.  On the right-hand side look for **Development** and click **Create a branch for this issue or link a pull request**
4.  Don't change the name of the branch, it should have the same name as the issue
5.  Choose what's next and click **Create branch**

## Commit Conventions

### Commit name

```
[action]: brief description
```

### Action types

- feat : introduces a new feature to codebase
- fix : patches a bug in codebase
- chore 
- build
- etc.

For more information see this [website](https://www.conventionalcommits.org/en/v1.0.0/)

## Pull requests

After finishing implementing your task, you can issue a pull request for it to be reviewd and pushed on the main branch.

### How to issue a pull request ?

1.  Make sure you commited all your work
2.  Go to GitHub and select your branch
3.  A yellow note with a green button that says Compare and pull request should appear, click it
4.  Give it a name and a useful description
5.  Select a reviewer (if applicable)
6.  Select the assignee and labels accordingly
7.  Click create pull request

> [!NOTE] 
> Now someone will have to look at your code, see if it's ok and if so accept the new changes on the main branch. If something isn't right then you will have to fix the problems and comeback with another pull request
