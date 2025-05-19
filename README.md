# cli-task-tracker
https://roadmap.sh/projects/task-tracker

CLI app to track your tasks and manage your to-do list

Possible commands:
Adding a new task
 - add "Lorem ipsum"
# Output: Task added successfully (ID: 1)

Updating and deleting tasks by id
- update 1 "Lorem ipsum"
- delete 1

Marking a task as in progress or done by id
- mark-in-progress 1
- mark-done 1

Listing all tasks
- list

Listing tasks by status
- list done
- list todo
- list in-progress