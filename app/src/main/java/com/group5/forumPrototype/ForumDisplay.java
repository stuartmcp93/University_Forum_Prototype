package com.group5.forumPrototype;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ForumDisplay extends Activity {
    Spinner studentSpinner, filterSpinner;
    ArrayAdapter<CharSequence> studentAdapter;
    ArrayAdapter<CharSequence> filterAdapter;
    Button backToModuleSelBtn, addPostBtn;
    ListView forumPostsDisplay;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_display);

        studentSpinner = (Spinner) findViewById(R.id.studentSpinner);
        studentAdapter = ArrayAdapter.createFromResource(this, R.array.student_spinner, android.R.layout.simple_spinner_item);
        studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        studentSpinner.setAdapter(studentAdapter);

        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);
        filterAdapter = ArrayAdapter.createFromResource(this, R.array.filter_spinner, android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSpinner.setAdapter(filterAdapter);


        //Fill Announcements display with dummy data for prototype visualisation
        ListView announcementsDisplay = findViewById(R.id.announcementsDisplay);
        ArrayList<String> announcements = new ArrayList<>();
        announcements.add("Week 8: Coursework deadline extended 10/4/21");
        announcements.add("Guest lecture - Jeff Besoz from Amazon Web Services.");
        announcements.add("Week 7: videos available");
        announcements.add("Changes to assignment");
        announcements.add("Please submit coursework 1");
        announcements.add("Welcome to CS991");
        ArrayAdapter adapterAnnounce = new ArrayAdapter(this, android.R.layout.simple_list_item_1, announcements);
        announcementsDisplay.setAdapter(adapterAnnounce);

        forumPostsDisplay = findViewById(R.id.forumPostsDisplay);

        addFakePostsOnStart();
        addPostToList();

        ArrayAdapter adapterForum = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListHolder.getInstance().forumDisplayString);
        forumPostsDisplay.setAdapter(adapterForum);


        //Select back to module
        backToModuleSelBtn = findViewById(R.id.backToModuleSelButton);

        //add post
        addPostBtn = findViewById(R.id.addPost);


        forumPostsDisplay.setOnItemClickListener((parent, view, position, id) -> {
            Intent displayForumPostIntent = new Intent(view.getContext(), DisplayForumPost.class);

            for(int i = 0; i <= 12; i++){
                if(i == position){
                    //displayForumPostIntent.putExtra("Title", forumPostTitles.get(i));
                    displayForumPostIntent.putExtra("Title", ListHolder.getInstance().forumPostsListTitle.get(i));
                    displayForumPostIntent.putExtra("Author", ListHolder.getInstance().forumPostAuthor.get(i));
                    displayForumPostIntent.putExtra("Date", ListHolder.getInstance().forumPostDate.get(i));
                    //displayForumPostIntent.putExtra("Comments", forumPostComments.get(i));
                    displayForumPostIntent.putExtra("Content", ListHolder.getInstance().forumPostsListContent.get(i));
                    break;
                }
            }
            startActivity(displayForumPostIntent);

        });


        addPostBtn.setOnClickListener(v -> addButton());

    }



    public void backToModuleSelect(View view) {
        Intent openModuleSel = new Intent(this, SelectModule.class);
        startActivity(openModuleSel);
    }

    public void addButton() {
        Intent addPostIntent = new Intent(this, AddPost.class);
        startActivity(addPostIntent);
    }

    //Start application with some fake posts for visualisation purposes
    public void addFakePostsOnStart(){
        if(ListHolder.getInstance().forumDisplayString.size() == 0){
            //Fake post 1
            ListHolder.getInstance().forumPostsListTitle.add(0,  "JavaDocs in Android Studio?");
            ListHolder.getInstance().forumPostAuthor.add(0,"Joe Bloggs");
            ListHolder.getInstance().forumPostDate.add(0, "[12-03-21]");
            ListHolder.getInstance().forumPostsListContent.add(0, "How to generate JavaDocs in Android studio can't seem to figure it out.\n Thanks!");
            ListHolder.getInstance().forumDisplayString.add(0, ListHolder.getInstance().forumPostsListTitle.get(0) + "\n"+ ListHolder.getInstance().forumPostAuthor.get(0) + " " + ListHolder.getInstance().forumPostDate.get(0));

            //Fake post 2
            ListHolder.getInstance().forumPostsListTitle.add(0,  "Question 4 help!!!");
            ListHolder.getInstance().forumPostAuthor.add(0,"Sarah Brown");
            ListHolder.getInstance().forumPostDate.add(0, "[18-03-21]");
            ListHolder.getInstance().forumPostsListContent.add(0, "Anyone know how to return an object in Java?");
            ListHolder.getInstance().forumDisplayString.add(0, ListHolder.getInstance().forumPostsListTitle.get(0) + "\n"+ ListHolder.getInstance().forumPostAuthor.get(0) + " " + ListHolder.getInstance().forumPostDate.get(0));
        }
    }

    public void addPostToList() {
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();

            ListHolder.getInstance().forumPostsListTitle.add(0,  extras.getString("Title"));
            ListHolder.getInstance().forumPostAuthor.add(0, extras.getString("Author"));
            ListHolder.getInstance().forumPostDate.add(0, "[" + extras.getString("Date") + "]");
            ListHolder.getInstance().forumPostsListContent.add(0, extras.getString("Content"));
            ListHolder.getInstance().forumDisplayString.add(0, ListHolder.getInstance().forumPostsListTitle.get(0) + "\n"+ ListHolder.getInstance().forumPostAuthor.get(0) + " " + ListHolder.getInstance().forumPostDate.get(0));
        }
    }




}
