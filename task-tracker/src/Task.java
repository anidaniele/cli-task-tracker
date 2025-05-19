import java.time.LocalDateTime;

public class Task {

        private int id;
        private String description;
        private Status status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private static int counter = 0;


        public Task(String description) {
                this.id = counter++;
                this.description = description;
                this.status = Status.TODO;
                this.createdAt = LocalDateTime.now();
                this.updatedAt = createdAt;
        }

        public Task(int id, String description, Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
                this.id = id;
                this.description = description;
                this.status = status;
                this.createdAt = createdAt;
                this.updatedAt = updatedAt;
                if (id >= counter) {
                        counter = id + 1;
                }
        }

        public int getId() {
                return id;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Status getStatus() {
                return status;
        }

        public void setStatus(Status status) {
                this.status = status;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
                this.updatedAt = updatedAt;
        }

        @Override
        public String toString() {
                return '{' +
                        "\"id\": " + id +
                        ", \"description\": \"" + description + '\"' +
                        ", \"status\": \"" + status + '\"' +
                        ", \"createdAt\": \"" + createdAt + '\"' +
                        ", \"updatedAt\": \"" + updatedAt + '\"' +
                        '}';
        }
}