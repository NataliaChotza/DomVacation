package dom.vacation.domvacation;


import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class SearchPageController {
    @FXML

    Circle c1 = new Circle();
    @FXML

    Circle c2 = new Circle();
    @FXML

    Circle c3 = new Circle();
    @FXML

    Circle c4 = new Circle();
    @FXML

    Circle c5 = new Circle();
    @FXML
    Label button;

    List<Circle> circle_list = new ArrayList<>();
    public SearchPageController(){
    }

    public static void startAnimation(){
        startAnimation.setCycleCount(Timeline.INDEFINITE);
        startAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(5), new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("Searching connections..");
                MainPageController.getSearchStage().close();
                System.out.println("Found connections..");
                //wyslwietl nowe okno ze znaleznionymi lotami
            }
        }));
        startAnimation.play();
    }

    public void addCircles(){
        this.circle_list.add(this.c1);
        this.circle_list.add(this.c2);
        this.circle_list.add(this.c3);
        this.circle_list.add(this.c4);
        this.circle_list.add(this.c5);
    }
    int count=0;
    int duration=1;
    static Timeline startAnimation = new Timeline();

    @FXML
    public void playAnimation() throws InterruptedException {
        addCircles();

        if (count == 0) {
            for (Circle circle : this.circle_list) {
                System.out.println(circle);
                FadeTransition fdt1 = new FadeTransition();
                fdt1.setNode(circle);
                fdt1.setCycleCount(100);
                fdt1.setDuration(Duration.seconds(duration + 0.5));
                fdt1.setFromValue(5.0);
                fdt1.setToValue(0.0);

                fdt1.setAutoReverse(true);
                fdt1.play();
                count++;
                duration++;
            }

        }
    }





}



