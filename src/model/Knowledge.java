package model;

public class Knowledge {

    private int id;
    private String question;
    private String answer;

    // Default constructor
    public Knowledge() {
    }

    // Parameterized constructor
    public Knowledge(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    // Constructor without ID
    public Knowledge(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}