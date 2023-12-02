package whereLocker;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Model model = new Model();
                View view = new View();
                Controller controller = new Controller();

                controller.setModel(model);
                controller.setView(view);

                view.setController(controller);
                view.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
