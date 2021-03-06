/*
 * Copyright (C) 2016 Alexandros Kantas 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package synchronizedbuttonsgrid;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Creates the Interactive Grid
 *
 * @author Alexandros Kantas
 */
public class InteractiveGrid extends Stage {

    private final Label gridnamelbl = new Label();
    private final Button dltbtn = new Button("Delete Grid");
    private final GridPane buttonsGrid = new GridPane();
    private final BorderPane border = new BorderPane();
    
    int i, column, row;
    private Button btnn;

    public InteractiveGrid(int Rows, int Columns, String name) {
        BorderPane.setAlignment(gridnamelbl, Pos.CENTER);
        BorderPane.setMargin(gridnamelbl, new Insets(10));

        BorderPane.setAlignment(dltbtn, Pos.CENTER_LEFT);
        BorderPane.setMargin(dltbtn, new Insets(10));

        BorderPane.setMargin(buttonsGrid, new Insets(25));
        for (int j = 0; j < Columns; j++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            buttonsGrid.getColumnConstraints().add(cc);
        }

        for (int j = 0; j < Rows; j++) {
            RowConstraints rc = new RowConstraints();
            rc.setVgrow(Priority.ALWAYS);
            buttonsGrid.getRowConstraints().add(rc);
        }

        gridnamelbl.setText(name);
        border.setTop(gridnamelbl);

        border.setBottom(dltbtn);

        for (row = 0; row < Rows; row++) {
            for (column = 0; column < Columns; column++) {
                buttonsGrid.add(new GridButton("" + i++), column, row);
            }
        }

        buttonsGrid.setHgap(2);
        buttonsGrid.setVgap(2);
        buttonsGrid.setAlignment(Pos.CENTER);
        border.setCenter(buttonsGrid);
        Scene scene = new Scene(border);
        scene.getStylesheets().add(getClass().getResource("StyleSheet.css").toExternalForm());
        setScene(scene);
        setTitle(name);
        show();
    }

    /**
     * Creates a button with max width and height so fit to GridPane
     */
    class GridButton extends Button {

        GridButton(String text) {
            super(text);
            setMaxHeight(Double.MAX_VALUE);
            setMaxWidth(Double.MAX_VALUE);
            getStyleClass().add("gridButton");

            setOnMouseClicked(e -> {
                Button btn = (Button) e.getSource();
                String x1 = btn.getText();
                System.out.println("You clicked button " + x1);
            });
            setOnMouseEntered(e -> {
                Button btn = (Button) e.getSource();
                String x = btn.getText();
                System.out.println("You entered in button " + x);
            });
            setOnMousePressed(e -> {
                Button btn = (Button) e.getSource();
                int foo = Integer.parseInt(btn.getText());
                String x = btn.getText();
                System.out.println("You pressing button " + x);
                btnn = (Button) buttonsGrid.getChildren().get(foo - 1);
                btnn.setStyle("-fx-color: -fx-pressed-base;");
            });
            setOnMouseReleased(e -> {
                btnn.setStyle("");
            });
        }
    }
}
