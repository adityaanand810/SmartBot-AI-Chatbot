package engine;

import dao.KnowledgeDAO;
import exception.InvalidInputException;

import java.util.HashMap;
import java.util.Map;

// RUBRIC: Polymorphism
// RuleBasedEngine ChatEngine interface ko implement karta hai.
//
// RUBRIC: Collections
// HashMap ka use knowledge base store karne ke liye kiya gaya hai.

public class RuleBasedEngine implements ChatEngine {

    private final Map<String, String> knowledgeBase;

    // Constructor
    public RuleBasedEngine() throws Exception {

        knowledgeBase = new HashMap<>();

        loadKnowledge();
    }

    // ==========================================
    // LOAD KNOWLEDGE FROM DATABASE
    // ==========================================

    private void loadKnowledge() throws Exception {

        KnowledgeDAO knowledgeDAO = new KnowledgeDAO();

        Map<String, String> data = knowledgeDAO.getAll();

        for (Map.Entry<String, String> entry : data.entrySet()) {

            String question = entry.getKey().toLowerCase().trim();
            String answer = entry.getValue();

            knowledgeBase.put(question, answer);
        }
    }

    // ==========================================
    // GET BOT RESPONSE
    // ==========================================

    @Override
    public String getResponse(String question)
            throws InvalidInputException {

        // RUBRIC: Exception Handling
        if (question == null || question.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Question cannot be empty."
            );
        }

        String userQuestion =
                question.toLowerCase().trim();

        // Exact match
        if (knowledgeBase.containsKey(userQuestion)) {

            return knowledgeBase.get(userQuestion);
        }

        // Keyword match
        for (Map.Entry<String, String> entry
                : knowledgeBase.entrySet()) {

            String keyword = entry.getKey();

            if (userQuestion.contains(keyword)) {

                return entry.getValue();
            }
        }

        return "Sorry, I don't know the answer to that question.";
    }

    // ==========================================
    // RELOAD KNOWLEDGE
    // ==========================================

    public void reloadKnowledge() throws Exception {

        knowledgeBase.clear();

        loadKnowledge();
    }

    // ==========================================
    // GET KNOWLEDGE SIZE
    // ==========================================

    public int getKnowledgeSize() {

        return knowledgeBase.size();
    }
}