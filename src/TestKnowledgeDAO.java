import dao.KnowledgeDAO;
import java.util.List;
import model.Knowledge;

public class TestKnowledgeDAO {

    public static void main(String[] args) {

        try {

            KnowledgeDAO dao = new KnowledgeDAO();

            List<Knowledge> list = dao.getAllKnowledge();

            System.out.println("===== KNOWLEDGE BASE =====");

            for (Knowledge knowledge : list) {
                System.out.println(
                        knowledge.getId() +
                        " | " +
                        knowledge.getQuestion() +
                        " | " +
                        knowledge.getAnswer()
                );
            }

        } catch (Exception e) {

            System.out.println("Error while reading knowledge:");
            e.printStackTrace();
        }
    }
}