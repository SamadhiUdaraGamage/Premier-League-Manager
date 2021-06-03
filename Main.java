import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Random;


public class Main extends Application {
    Stage window;
    TableView<FootballClub> tableView;
    PremierLeagueManager plm= null;
    Scanner scanner;

    public static void main(String[] args) {

        launch();

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        File filename = new File("./premierLeague.ser");
        if (filename.exists()) {
            try {
                // Reading the object from a file
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                // Method for deserialization of object
                plm = (PremierLeagueManager) in.readObject();
                display();
                in.close();
                file.close();

                System.out.println("Object has been deserialized ");
//                System.out.println("size = " + premierLeagueManager.clubList.size());

            } catch (IOException ex) {
                System.out.println("IOException is caught");
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException is caught");
            }


        } else {

            plm = new PremierLeagueManager();
            display();
        }
    }

    public boolean display() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Press 1: Create a new football club and add it to premier league ");
            System.out.println("Press 2: Delete an existing club from premier league ");
            System.out.println("Press 3: Display statistics for club ");
            System.out.println("Press 4: Display Premier League Table ");
            System.out.println("Press 5: Add Played A Match");
            System.out.println("Press 6: Run GUI");
            String line = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(line);
            } catch (Exception e) {

            }
            switch (command) {
                case 1:
                    Addfootballclub();
                    break;
                case 2:
                    DeleteFootballClub();
                    break;
                case 3:
                    DisplayStatistics();
                    break;
                case 4:
                    DisplayPremierLeagueTable();
                    break;
                case 5:
                    AddPlayedMatch();
                    break;
                case 6:
                    RunGUI();
                    break;

                    default:
                    System.out.println("Wrong command");
            }

        }
    }



    //add football club

    private void Addfootballclub() {


        FootballClub club;
        System.out.println("Enter your club name: ");
        String name = scanner.nextLine();


        System.out.println("Enter your club location");
        String location = scanner.nextLine();

        club = new FootballClub(name, location);

        if (plm.addfootballclub(club)) {
            System.out.println("This club is successfully added to the league");
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("This club is already exist");
            System.out.println("-------------------------------------------------");
        }
        saveFile();
    }

    //delete football club

    private void DeleteFootballClub() {
        System.out.println("Enter your club name: ");
        String line = scanner.nextLine();

        if (plm.deleteFootballClub(line)) {
            System.out.println("Deleted Successfully");
        } else {
            System.out.println("Team is not exist");
        }
        saveFile();
    }

    //display statistics

    private void DisplayStatistics() {

        System.out.println("Insert your club name: ");
        String line = scanner.nextLine();
        FootballClub ft = null;
        for (FootballClub footballClub : plm.league) {
            if (footballClub.getName().equals(line)) {
                ft = footballClub;
            }
        }
        if (ft != null) {
            System.out.println("FootballClub " + ft.getName() + " Won matches: " + ft.getWinCount());
            System.out.println("FootballClub " + ft.getName() + " Draw matches: " + ft.getDrawCount());
            System.out.println("FootballClub " + ft.getName() + " Defeat matches: " + ft.getDefeatCount());
            System.out.println("FootballClub " + ft.getName() + " ReceivedGoals : " + ft.getReceivedGoalsCount());
            System.out.println("FootballClub " + ft.getName() + " ScoredGoals : " + ft.getScoredGoalsCount());
            System.out.println("FootballClub " + ft.getName() + " Points : " + ft.getPoints());
            System.out.println("FootballClub " + ft.getName() + " MatchesPlayed : " + ft.getMatchesPlayed());
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("No such club in league");
            System.out.println("-------------------------------------------------");

        }

    }

    //display PremierLeagueTable

    private void DisplayPremierLeagueTable() {

        Collections.sort(plm.league, new CusComparator());
        for (FootballClub footballClub : plm.league) {
            System.out.println("Club Name: " + footballClub.getName() + " Points: " + footballClub.getPoints() + " Goal Difference: " + (footballClub.getScoredGoalsCount() - footballClub.getReceivedGoalsCount()));
        }
        System.out.println("-------------------------------------------------");

    }

    //display add played match

    private void AddPlayedMatch() {

        System.out.println("Please Enter Date (format yyyy/mm/dd): ");
        String line = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("yyyy/mm/dd").parse(line);

        } catch (ParseException ex) {
            System.out.println("you have to enter date correct format");
            return;
        }

        System.out.println("Please Enter Home Team Name: ");
        line = scanner.nextLine();
        FootballClub Home = null;
        for (FootballClub footballClub : plm.league) {
            if (footballClub.getName().equals(line))
                Home = footballClub;
        }
        if (Home == null) {
            System.out.println("No such club in league");
            return;
        }
        System.out.println("Please Enter Away Team Name: ");
        line = scanner.nextLine();
        FootballClub Away = null;
        for (FootballClub footballClub : plm.league) {
            if (footballClub.getName().equals(line))
                Away = footballClub;
        }
        if (Away == null) {
            System.out.println("No such club in league");
            return;
        }
        System.out.println("Enter Home team goals: ");
        line = scanner.nextLine();
        int HomeGoals = -1;
        try {
            HomeGoals = Integer.parseInt(line);
        } catch (Exception exception) {

        }
        if (HomeGoals == -1) {
            System.out.println("You have to enter number of home goals");
            return;
        }
        System.out.println("Enter Away team goals: ");
        line = scanner.nextLine();
        int AwayGoals = -1;
        try {
            AwayGoals = Integer.parseInt(line);
        } catch (Exception exception) {


        }
        if (AwayGoals == -1) {
            System.out.println("You have to enter number of away goals");
            return;
        }
        Match match = new Match();
        match.setName1(Home.getName());
        match.setName2(Away.getName());


        match.setDate(date);
        match.setTeamX(Home);
        match.setTeamY(Away);
        match.setTeamXScore(AwayGoals);
        match.setTeamYScore(HomeGoals);

        Home.setScoredGoalsCount(Home.getScoredGoalsCount() + HomeGoals);
        Away.setScoredGoalsCount(Away.getScoredGoalsCount() + AwayGoals);
        Home.setReceivedGoalsCount(Home.getReceivedGoalsCount() + AwayGoals);
        Away.setReceivedGoalsCount(Away.getReceivedGoalsCount() + HomeGoals);

        if (HomeGoals > AwayGoals) {
            Home.setPoints(Home.getPoints() + 3);
            Home.setWinCount(Home.getWinCount() + 1);
            Away.setDefeatCount(Away.getDefeatCount() + 1);
        } else if (HomeGoals < AwayGoals) {
            Away.setPoints(Away.getPoints() + 3);
            Away.setWinCount(Away.getWinCount() + 1);
            Home.setDefeatCount(Home.getDefeatCount() + 1);
        } else {
            Home.setPoints(Home.getPoints() + 1);
            Home.setDrawCount(Home.getDrawCount() + 1);
            Away.setPoints(Away.getPoints() + 1);
            Away.setDrawCount(Away.getDrawCount() + 1);
        }
        System.out.println("-------------------------------------------------");
        plm.AddPlayedMatch(match);
        saveFile();

    }

    //save file

   public void saveFile() {
        File filename = new File("./premierLeague.ser");

        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object
            out.writeObject(plm);
            out.close();
            file.close();
            System.out.println("Object has been serialized");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    private void RunGUI() {



        //Creating a table view
        TableView<FootballClub> teamtable=new TableView<>();
        ObservableList<FootballClub> teams=FXCollections.observableArrayList();

        //Setting the size of the table
        TableView tableView = new TableView();
        tableView.setLayoutX(95);
        tableView.setLayoutY(100);
        tableView.setPrefSize(1000, 500);


        //Creating columns
        TableColumn<FootballClub, String> NameColum = new TableColumn<>("Name");
        NameColum.setMaxWidth(400);
        NameColum.setCellValueFactory(new PropertyValueFactory<>("name"));


        TableColumn<FootballClub, Integer> WinCountColum=new TableColumn<>("WinCount");
        WinCountColum.setMaxWidth(400);
        WinCountColum.setCellValueFactory(new PropertyValueFactory<>("WinCount"));
        WinCountColum.setSortType(TableColumn.SortType.DESCENDING);

        TableColumn<FootballClub, Integer> DrawCountColum = new TableColumn<>("DrawCount");
        DrawCountColum.setMinWidth(100);
        DrawCountColum.setCellValueFactory(new PropertyValueFactory<>("drawCount"));

        TableColumn<FootballClub, Integer> DefeatCountColum = new TableColumn<>("DefeatCount");
        DefeatCountColum.setMinWidth(100);
        DefeatCountColum.setCellValueFactory(new PropertyValueFactory<>("defeatCount"));

        TableColumn<FootballClub, Integer> ReceivedGoalsCountColum = new TableColumn<>("ReceivedGoalsCount");
        ReceivedGoalsCountColum.setMinWidth(180);
        ReceivedGoalsCountColum.setCellValueFactory(new PropertyValueFactory<>("receivedGoalsCount"));

        TableColumn<FootballClub, Integer> ScoredGoalsCountColum = new TableColumn<>("ScoredGoalsCount");
        ScoredGoalsCountColum.setMinWidth(180);
        ScoredGoalsCountColum.setCellValueFactory(new PropertyValueFactory<>("scoredGoalsCount"));

        TableColumn<FootballClub, Integer> PointsColum = new TableColumn<>("Points");
        PointsColum.setMinWidth(80);
        PointsColum.setCellValueFactory(new PropertyValueFactory<>("points"));

        TableColumn<FootballClub, Integer> MatchesPlayedColum = new TableColumn<>("MatchesPlayed");
        MatchesPlayedColum.setMinWidth(130);
        MatchesPlayedColum.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));



        Random random=new Random();


        Button addbutton = new Button("Generate Match");


        addbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                generateRandomMatch();

            }
        });

        Button dbutton = new Button("Date Played ");

        HBox hBox10 = new HBox();
        hBox10.setPadding(new Insets(40, 100, 40, 100));
        hBox10.setSpacing(20);
        hBox10.getChildren().addAll( addbutton, dbutton);


        //tableView.setItems(getFootballClub());

        tableView.getColumns().add(NameColum);
        tableView.getColumns().add(WinCountColum);
        tableView.getColumns().add(DrawCountColum);
        tableView.getColumns().add(DefeatCountColum);
        tableView.getColumns().add(ReceivedGoalsCountColum);
        tableView.getColumns().add(ScoredGoalsCountColum);
        tableView.getColumns().add(PointsColum);
        tableView.getColumns().add(MatchesPlayedColum);


        VBox vBox = new VBox();
        vBox.getChildren().addAll(tableView, hBox10);

        for (FootballClub footballClub : plm.league) {
            tableView.getItems().add(footballClub);
        }


        Pane pane1 = new Pane();
        pane1.setStyle("-fx-background-color:#20B2AA");

        pane1.getChildren().add(tableView);
        pane1.getChildren().add(vBox);
        pane1.getChildren().add(hBox10);

        Button backbtton=new Button("Back");
        backbtton.setLayoutX(100);
        backbtton.setLayoutY(50);

        Label lb2=new Label("Search Date");
        lb2.setLayoutX(200);
        lb2.setLayoutY(52);

        Button searchbtton=new Button("Search");
        searchbtton.setLayoutX(600);
        searchbtton.setLayoutY(50);

        TextField tf1 = new TextField();
        tf1.setPromptText("YYYY/MM/DD");
        tf1.setLayoutX(350);
        tf1.setLayoutY(50);



        //Creating a table view
        TableView<Match> match=new TableView<>();
        ObservableList<Match> matches=FXCollections.observableArrayList();

        //Setting the size of the table
        TableView tableView1 = new TableView();
        tableView1.setLayoutX(95);
        tableView1.setLayoutY(100);
        tableView1.setPrefSize(1000, 500);


        //Creating columns
        TableColumn<Match, String> TeamXColum = new TableColumn<>("TeamX Name");
        TeamXColum.setMaxWidth(400);
        TeamXColum.setCellValueFactory(new PropertyValueFactory<>("name1"));

        TableColumn<Match, String> TeamYColum = new TableColumn<>("TeamY Name");
        TeamYColum.setMaxWidth(400);
        TeamYColum.setCellValueFactory(new PropertyValueFactory<>("name2"));

        TableColumn<Match, Integer> TeamXScoreColum=new TableColumn<>("TeamXScore");
        TeamXScoreColum.setMaxWidth(400);
        TeamXScoreColum.setCellValueFactory(new PropertyValueFactory<>("teamXScore"));
        TeamXScoreColum.setSortType(TableColumn.SortType.DESCENDING);

        TableColumn<Match, Integer> TeamYScoreColum = new TableColumn<>("TeamYScore");
        TeamYScoreColum.setMinWidth(100);
        TeamYScoreColum.setCellValueFactory(new PropertyValueFactory<>("teamYScore"));

        TableColumn<Match, Date> DateColum = new TableColumn<>("Date");
        DateColum.setMinWidth(300);
        DateColum.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView1.getColumns().add(TeamXColum);
        tableView1.getColumns().add(TeamYColum);
        tableView1.getColumns().add(TeamXScoreColum);
        tableView1.getColumns().add(TeamYScoreColum);
        tableView1.getColumns().add(DateColum);



        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(tableView1);

        for (Match match1 : plm.matchArrayList) {

            tableView1.getItems().add(match1);
        }

        searchbtton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                tableView1.getItems().clear();
                Match found = null;
                String seaching = tf1.getText();
                try {
                    found = plm.getMatchbyDate(new SimpleDateFormat("yyyy/mm/dd").parse(seaching));
                } catch (ParseException e) {
                    System.out.println("Your Format is wrong");
                }
                if(found != null) {
                    tableView1.getItems().add(found);

                    Alert a = new Alert(Alert.AlertType.NONE);
                    a.setAlertType(Alert.AlertType.CONFIRMATION);
                    a.setContentText("random match generated successfully !!!\n");
                    tf1.clear();
                }else {
                    System.out.println("No search results");
                }


            }
        });


        Pane pane2=new Pane();
        pane2.setStyle("-fx-background-color:#20B2AA");
        pane2.getChildren().add(backbtton);
        pane2.getChildren().add(searchbtton);
        pane2.getChildren().add(tf1);
        pane2.getChildren().add(lb2);
        pane2.getChildren().add(tableView1);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane1);

        dbutton.setOnAction(event -> {
            borderPane.setCenter(pane2);
        });

        backbtton.setOnAction(event -> {
            borderPane.setCenter(pane1);
        });


        //Setting the scene
        Stage stage = new Stage();
        Scene scene = new Scene(borderPane, 1200, 800);
        stage.setTitle("Football Management System");
        stage.setScene(scene);
        stage.showAndWait();


    }


    private void generateRandomMatch() {

            Random rm2=new Random();
            int rmitem1=rm2.nextInt(plm.league.size());
            FootballClub rmClub1=plm.league.get(rmitem1);

            System.out.println(""+ rmClub1.getName());
            boolean same = true;
            FootballClub rmClub2 = null;
            while(same){
                int rmitem2=rm2.nextInt(plm.league.size());
                if(rmitem2 == rmitem1){
                    same= true;
                }else{
                    same = false;
                    rmClub2=plm.league.get(rmitem2);
                    System.out.println(""+ rmClub2.getName());
                }
            }
            int scr1=rm2.nextInt(10);
            int scr2=rm2.nextInt(10);
            System.out.println(scr1);
            System.out.println(scr2);

        long sD = new Date("01/01/2020").getTime();
        long eD = new Date("01/01/2021").getTime();

//generate a random long with this start and end date
        Random r = new Random();
        long random = eD + (long) (r.nextDouble() * (sD - eD));

        Date resultdate = new Date(random);
        System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(resultdate));


        addRandomMatch(rmClub1,scr1,rmClub2,scr2,resultdate);


    }

    private void addRandomMatch(FootballClub Home,int HomeGoals,FootballClub Away,int AwayGoals,Date date) {
        Match match = new Match();
        match.setName1(Home.getName());
        match.setName2(Away.getName());
        match.setDate(date);
        match.setTeamX(Home);
        match.setTeamY(Away);
        match.setTeamXScore(AwayGoals);
        match.setTeamYScore(HomeGoals);

        Home.setScoredGoalsCount(Home.getScoredGoalsCount() + HomeGoals);
        Away.setScoredGoalsCount(Away.getScoredGoalsCount() + AwayGoals);
        Home.setReceivedGoalsCount(Home.getReceivedGoalsCount() + AwayGoals);
        Away.setReceivedGoalsCount(Away.getReceivedGoalsCount() + HomeGoals);

        if (HomeGoals > AwayGoals) {
            Home.setPoints(Home.getPoints() + 3);
            Home.setWinCount(Home.getWinCount() + 1);
            Away.setDefeatCount(Away.getDefeatCount() + 1);
        } else if (HomeGoals < AwayGoals) {
            Away.setPoints(Away.getPoints() + 3);
            Away.setWinCount(Away.getWinCount() + 1);
            Home.setDefeatCount(Home.getDefeatCount() + 1);
        } else {
            Home.setPoints(Home.getPoints() + 1);
            Home.setDrawCount(Home.getDrawCount() + 1);
            Away.setPoints(Away.getPoints() + 1);
            Away.setDrawCount(Away.getDrawCount() + 1);
        }
        plm.AddPlayedMatch(match);
        System.out.println("-------------------------------------------------");
        saveFile();



    }

    public ObservableList<FootballClub> getFootballClub() {
        ObservableList<FootballClub> footballClubs = FXCollections.observableArrayList();

        return footballClubs;

    }

    public ObservableList<Match> getMatch(){
        ObservableList<Match> matches=FXCollections.observableArrayList();
        return matches;
    }
}









