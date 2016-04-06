package com.example.wazifa.calendar;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Manny on 3/25/16.
 */
public class DBmanager {
    private static Firebase database;
    private static final String url = "https://ucalendar.firebaseio.com/";
    private ArrayList<Event> events;
    private static final String userpath="usr";
    private static final String eventpath="evnt";
    private User user;
    private Event event;

    public DBmanager()
    {
        if(database==null) {
            database = new Firebase(url);
        }else System.out.println("already created!");
        System.out.println("hello");
    }

    public DBmanager(Firebase database) {
        setDatabase(database);
    }


    public Firebase getDatabase() {

        return database;
    }

    public void setDatabase(Firebase database) {
        this.database = database;
    }

    @Override
    public String toString() {
        return url;
    }

    //TODO return valid user from database
    public User getUser(User usr) {
        user = usr;

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    User temp = dataSnapshot.child(userpath).child(user.getUsername()).getValue(User.class);
                    if (temp == null) throw new FirebaseException("Hello");
                    user.setPassword(temp.getPassword());
                    user.setEmail(temp.getEmail());
                    user.setName(temp.getName());
                    user.setSecurityquestion(temp.getSecurityquestion());
                } catch (FirebaseException ex) {
                    System.out.println("User doesn't exist!");
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.err.println("Cancelled!");
            }
        });

        return user;

    }

    public void putUser(User usr)
    {


        database.child(userpath).child(usr.getUsername()).setValue(usr, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                System.out.println("PUT USER IN DATADASE");
            }
        });
    }

    public void putEvent(User usr,Event eve)
    {
        database.child(eventpath).child(usr.getUsername()).child(eve.getDate()).setValue(eve, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                System.out.println("PUT EVEN IN DATABASE");
            }
        });
    }

    public Event getEvent(User u ,Event E)
    {
        user =u;
        event=E;

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Event temp = dataSnapshot.child(eventpath).child(user.getUsername()).child(event.getDate()).getValue(Event.class);
                    if (temp == null) throw new FirebaseException("Event doesn't exist!");
                    event.setDate(temp.getDate());
                    event.setTime(temp.getTime());
                    event.setLocation(temp.getLocation());
                    event.setTitle(temp.getTitle());

                } catch (FirebaseException ex) {
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Cancelled!");
            }
        });
        return event;

    }

    //TODO authenticate user
    public int authUser(String email, String pass) {
        System.out.println(email);
        System.out.println(pass);
        database.authWithPassword(email, pass, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("Logged IN!");
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                System.err.println("Failed to Login!");

            }

        });
        System.out.println(database.getAuth());
        if(database.getAuth() == null)return 0;
        else return 1;
    }

    public void createAccount(String email,String pass)
    {
        database.createUser(email, pass, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                System.out.println("Created user account!");
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                System.err.println("User's acount was not created!");
            }
        });
    }

    public ArrayList getAllEvents(User usr)
    {
        ArrayList<Event> eventList = new ArrayList<>();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " blog posts");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return eventList;

    }

}
