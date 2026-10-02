package engine;

// RUBRIC: Interface
// ChatEngine ek common contract define karta hai.
// RuleBasedEngine aur AIApiEngine is interface ko implement karenge.

public interface ChatEngine {

    // User ke question ka bot response return karega.
    // Exception isliye rakha hai taaki different engines
    // apni custom exceptions handle kar saken.
    String getResponse(String question) throws Exception;
}