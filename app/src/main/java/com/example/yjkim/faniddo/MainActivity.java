package com.example.yjkim.faniddo;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yjkim.faniddo.domain.BoardVO;
import com.example.yjkim.faniddo.domain.ConsultVO;
import com.example.yjkim.faniddo.domain.FatherBoardVO;
import com.example.yjkim.faniddo.domain.FatherVO;
import com.example.yjkim.faniddo.domain.FriendVO;
import com.example.yjkim.faniddo.domain.MemberVO;
import com.example.yjkim.faniddo.domain.MissionVO;
import com.example.yjkim.faniddo.domain.SFatherVO;
import com.example.yjkim.faniddo.domain.SFriendVO;
import com.example.yjkim.faniddo.domain.SonBoardVO;
import com.example.yjkim.faniddo.service.BoardService;
import com.example.yjkim.faniddo.service.ConsultService;
import com.example.yjkim.faniddo.service.FatherBoardService;
import com.example.yjkim.faniddo.service.FatherService;
import com.example.yjkim.faniddo.service.FriendService;
import com.example.yjkim.faniddo.service.MemberService;
import com.example.yjkim.faniddo.service.MissionService;
import com.example.yjkim.faniddo.service.SFatherService;
import com.example.yjkim.faniddo.service.SFriendService;
import com.example.yjkim.faniddo.service.SonBoardService;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int MY_PERMISSION_CAMERA = 1111;
    private static final int REQUEST_TAKE_PHOTO = 2222;
    private static final int REQUEST_TAKE_ALBUM = 3333;
    private static final int REQUEST_IMAGE_CROP = 4444;

    Uri imageUri;
    Uri photoURI, albumURI;
    String mCurrentPhotoPath;
    CircleImageView imageView;

    List<BoardVO> list;
    List<MissionVO> mslist;
    List<ConsultVO> clist;
    List<SFriendVO> sflist;
    List<SFatherVO> sfalist;
    List<FatherBoardVO> fblist;
    List<SonBoardVO> sblist;
    TextView puserid, setting_id, setting_name, setting_email, friend_add_id, father_add_id;
    String userdata, profilename, refreshprofilename;
    int mno, fno, mynumber, fano;

    ImageView ivimg, profileimg;
    Map<Integer, View> map = new HashMap<>();
    MissionAdaper missionAdaper;
    RecyclerView rv, consultrv, flistrv, falistrv, fatherboardrv, sonboardrv;
    FatherListAdapter fatherListAdapter;
    FriendListAdapter friendListAdapter;
    ConsultAdapter consultAdapter;
    RecyclerView.LayoutManager cLayoutManager, fbLayoutManager, sbLayoutManager;
    FatherBoardAdapter fatherBoardAdapter;
    SonBoardAdapter sonBoardAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout, swipeconsult, swipe_fb, swipe_sb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipe_sb = (SwipeRefreshLayout) findViewById(R.id.swipe_sb);
        swipe_sb.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_sb.setRefreshing(false);
                sonBoardAdapter = new SonBoardAdapter(MainActivity.this, userdata);
                sonboardrv = (RecyclerView) findViewById(R.id.rvson);
                sbLayoutManager = new LinearLayoutManager(MainActivity.this);
                sonboardrv.setLayoutManager(sbLayoutManager);
                sonboardrv.setAdapter(sonBoardAdapter);

                DividerItemDecoration sbdividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
                sbdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
                sonboardrv.addItemDecoration(sbdividerItemDecoration);

                SonBoardService sbservice = SonBoardService.retrofit.create(SonBoardService.class);
                Call<List<SonBoardVO>> sbcall = sbservice.show();
                sbcall.enqueue(new Callback<List<SonBoardVO>>() {
                    @Override
                    public void onResponse(Call<List<SonBoardVO>> call, Response<List<SonBoardVO>> response) {
                        sblist = response.body();
                        for (int i = 0; i < sblist.size(); i++) {
                            sonBoardAdapter.add(sblist.get(i));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SonBoardVO>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


            }
        });

        swipe_sb.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_general);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                final CardBoardAdapter cardBoardAdapter = new CardBoardAdapter(MainActivity.this);
                recyclerView.setAdapter(cardBoardAdapter);

                BoardService service = BoardService.retrofit.create(BoardService.class);
                Call<List<BoardVO>> call = service.showlist();
                call.enqueue(new Callback<List<BoardVO>>() {
                    @Override
                    public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
                        list = response.body();
                        for (int i = 0; i < list.size(); i++) {
                            cardBoardAdapter.add(list.get(i));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        swipeconsult = (SwipeRefreshLayout) findViewById(R.id.swipe_consult);
        swipeconsult.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                consultAdapter = new ConsultAdapter(MainActivity.this, userdata);
                consultrv = (RecyclerView) findViewById(R.id.rvconsult);
                cLayoutManager = new LinearLayoutManager(MainActivity.this);
                consultrv.setLayoutManager(cLayoutManager);
                consultrv.setAdapter(consultAdapter);

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
                dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
                consultrv.addItemDecoration(dividerItemDecoration);

                ConsultService service = ConsultService.retrofit.create(ConsultService.class);
                Call<List<ConsultVO>> call = service.show();
                call.enqueue(new Callback<List<ConsultVO>>() {
                    @Override
                    public void onResponse(Call<List<ConsultVO>> call, Response<List<ConsultVO>> response) {
                        clist = response.body();
                        Log.d("LOG CLIST", "" + clist);
                        for (int i = 0; i < clist.size(); i++) {
                            consultAdapter.add(clist.get(i));
                        }
                        swipeconsult.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<ConsultVO>> call, Throwable t) {

                    }
                });
            }
        });

        swipeconsult.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        swipe_fb = (SwipeRefreshLayout) findViewById(R.id.swipe_fb);
        swipe_fb.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_fb.setRefreshing(false);
                fatherBoardAdapter = new FatherBoardAdapter(MainActivity.this, userdata);
                fatherboardrv = (RecyclerView) findViewById(R.id.rvfather);
                fbLayoutManager = new LinearLayoutManager(MainActivity.this);
                fatherboardrv.setLayoutManager(fbLayoutManager);
                fatherboardrv.setAdapter(fatherBoardAdapter);

                DividerItemDecoration fdividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
                fdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
                fatherboardrv.addItemDecoration(fdividerItemDecoration);

                FatherBoardService fbservice = FatherBoardService.retrofit.create(FatherBoardService.class);
                Call<List<FatherBoardVO>> fbcall = fbservice.show();
                fbcall.enqueue(new Callback<List<FatherBoardVO>>() {
                    @Override
                    public void onResponse(Call<List<FatherBoardVO>> call, Response<List<FatherBoardVO>> response) {
                        fblist = response.body();
                        for (int i = 0; i < fblist.size(); i++) {
                            fatherBoardAdapter.add(fblist.get(i));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<FatherBoardVO>> call, Throwable t) {

                    }
                });
            }
        });

        swipe_fb.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        imageView = (CircleImageView) findViewById(R.id.updateimgview);
        friend_add_id = (TextView) findViewById(R.id.friend_add_id);
        father_add_id = (TextView) findViewById(R.id.father_add_id);

        FloatingActionButton sonfab = (FloatingActionButton) findViewById(R.id.sonfab);
        sonfab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SonBoardRegisterActivity.class);
                intent.putExtra("writer", userdata);
                startActivity(intent);
            }
        });

        FloatingActionButton fatherfab = (FloatingActionButton) findViewById(R.id.fatherfab);
        fatherfab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FatherBoardRegisterActivity.class);
                intent.putExtra("writer", userdata);
                startActivity(intent);
            }
        });


        FloatingActionButton consultfab = (FloatingActionButton) findViewById(R.id.consultfab);
        consultfab.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConsultRegisterActivity.class);
                intent.putExtra("writer", userdata);
                startActivity(intent);
            }
        });

        com.github.clans.fab.FloatingActionButton fabsearch = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabsearch);
        fabsearch.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MissionSearchActivity.class);
                intent.putExtra("writer", userdata);
                startActivity(intent);
            }
        });

        com.github.clans.fab.FloatingActionButton fabmissionadd = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabmissionadd);
        fabmissionadd.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MissionAddActivity.class);
                intent.putExtra("writer", userdata);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabadd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BoardActivity.class);
                intent.putExtra("writer", userdata);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Drawer toggle = new Drawer(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        puserid = (TextView) navHeaderView.findViewById(R.id.puserid);
        profileimg = (ImageView) navHeaderView.findViewById(R.id.imageView);

        Intent intent = getIntent();
        userdata = intent.getStringExtra("userid");
        puserid.setText(userdata);
        MemberService fileservice = MemberService.retrofit.create(MemberService.class);
        Call<ResponseBody> filecall = fileservice.showfile(userdata);
        filecall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    profilename = response.body().string();
                    if (profilename.equals("null")) {
                        Log.d("-----------", profilename);
                    } else {
                        Glide.with(MainActivity.this).load("http://192.168.43.11:8080/member/display?fileName=" + profilename).into(profileimg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

        toggle.setRefreshCall(new Drawer.RefreshCall() {
            public void refresh() {
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                View hView = navigationView.getHeaderView(0);

                puserid = (TextView) hView.findViewById(R.id.puserid);
                puserid.setText(userdata);

                MemberService fileservice = MemberService.retrofit.create(MemberService.class);
                Call<ResponseBody> call = fileservice.showfile(userdata);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            refreshprofilename = response.body().string();
                            if (refreshprofilename.equals("null")) {
                                Log.d("-----------", profilename);
                            } else {
                                Glide.with(MainActivity.this).load("http://192.168.43.11:8080/member/display?fileName=" + refreshprofilename).into(profileimg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        Button letter_write = (Button) findViewById(R.id.letter_write);
        letter_write.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LetterWriteActivity.class);
                intent.putExtra("lwriter", userdata);
                startActivity(intent);
            }
        });

        Button letter_search = (Button) findViewById(R.id.letter_search);
        letter_search.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LetterSearchActivity.class);
                intent.putExtra("lwriter", userdata);
                startActivity(intent);
            }
        });

        ivimg = (ImageView) findViewById(R.id.ivimg);
        recyclerView = (RecyclerView) findViewById(R.id.rvimg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CardBoardAdapter cardBoardAdapter = new CardBoardAdapter(this);
        recyclerView.setAdapter(cardBoardAdapter);

        BoardService service = BoardService.retrofit.create(BoardService.class);
        Call<List<BoardVO>> call = service.showlist();
        call.enqueue(new Callback<List<BoardVO>>() {
            @Override
            public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
                list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    cardBoardAdapter.add(list.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        View v_letter = (View) findViewById(R.id.letter);
        View v_mission = (View) findViewById(R.id.mission);
        View v_consult = (View) findViewById(R.id.consult);
        View v_profile = (View) findViewById(R.id.profile);
        View v_friend_add = (View) findViewById(R.id.friend_add);
        View v_friend_list = (View) findViewById(R.id.friend_list);
        View v_father_board = (View) findViewById(R.id.father_board);
        View v_son_board = (View) findViewById(R.id.son_board);

        map.put(R.id.nav_general, v_letter);
        map.put(R.id.nav_mission, v_mission);
        map.put(R.id.nav_consult, v_consult);
        map.put(R.id.nav_manage, v_profile);
        map.put(R.id.nav_friend_add, v_friend_add);
        map.put(R.id.nav_friend, v_friend_list);
        map.put(R.id.nav_father, v_father_board);
        map.put(R.id.nav_son, v_son_board);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sonBoardAdapter = new SonBoardAdapter(MainActivity.this, userdata);
        sonboardrv = (RecyclerView) findViewById(R.id.rvson);
        sbLayoutManager = new LinearLayoutManager(MainActivity.this);
        sonboardrv.setLayoutManager(sbLayoutManager);
        sonboardrv.setAdapter(sonBoardAdapter);

        DividerItemDecoration sbdividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        sbdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
        sonboardrv.addItemDecoration(sbdividerItemDecoration);

        SonBoardService sbservice = SonBoardService.retrofit.create(SonBoardService.class);
        Call<List<SonBoardVO>> sbcall = sbservice.show();
        sbcall.enqueue(new Callback<List<SonBoardVO>>() {
            @Override
            public void onResponse(Call<List<SonBoardVO>> call, Response<List<SonBoardVO>> response) {
                sblist = response.body();
                for (int i = 0; i < sblist.size(); i++) {
                    sonBoardAdapter.add(sblist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<SonBoardVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


        fatherBoardAdapter = new FatherBoardAdapter(MainActivity.this, userdata);
        fatherboardrv = (RecyclerView) findViewById(R.id.rvfather);
        fbLayoutManager = new LinearLayoutManager(MainActivity.this);
        fatherboardrv.setLayoutManager(fbLayoutManager);
        fatherboardrv.setAdapter(fatherBoardAdapter);

        DividerItemDecoration fdividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        fdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
        fatherboardrv.addItemDecoration(fdividerItemDecoration);

        FatherBoardService fbservice = FatherBoardService.retrofit.create(FatherBoardService.class);
        Call<List<FatherBoardVO>> fbcall = fbservice.show();
        fbcall.enqueue(new Callback<List<FatherBoardVO>>() {
            @Override
            public void onResponse(Call<List<FatherBoardVO>> call, Response<List<FatherBoardVO>> response) {
                fblist = response.body();
                for (int i = 0; i < fblist.size(); i++) {
                    fatherBoardAdapter.add(fblist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<FatherBoardVO>> call, Throwable t) {

            }
        });


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvimg);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CardBoardAdapter cardBoardAdapter = new CardBoardAdapter(this);
        recyclerView.setAdapter(cardBoardAdapter);

        BoardService service = BoardService.retrofit.create(BoardService.class);
        Call<List<BoardVO>> call = service.showlist();
        call.enqueue(new Callback<List<BoardVO>>() {
            @Override
            public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
                list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    cardBoardAdapter.add(list.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        rv = (RecyclerView) findViewById(R.id.rvmission);
        rv.setLayoutManager(new LinearLayoutManager(this));
        missionAdaper = new MissionAdaper(this);
        rv.setAdapter(missionAdaper);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
        dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
        rv.addItemDecoration(dividerItemDecoration);

        MissionService msv = MissionService.retrofit.create(MissionService.class);
        Call<List<MissionVO>> mcall = msv.showmission(userdata);
        mcall.enqueue(new Callback<List<MissionVO>>() {
            @Override
            public void onResponse(Call<List<MissionVO>> call, Response<List<MissionVO>> response) {
                mslist = response.body();
                Log.d("LOG", "" + mslist);
                for (int i = 0; i < mslist.size(); i++) {
                    missionAdaper.add(mslist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<MissionVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        consultAdapter = new ConsultAdapter(MainActivity.this, userdata);
        consultrv = (RecyclerView) findViewById(R.id.rvconsult);
        cLayoutManager = new LinearLayoutManager(this);
        consultrv.setLayoutManager(cLayoutManager);
        consultrv.setAdapter(consultAdapter);

        DividerItemDecoration cdividerItemDecoration = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
        cdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
        consultrv.addItemDecoration(cdividerItemDecoration);

        ConsultService cservice = ConsultService.retrofit.create(ConsultService.class);
        Call<List<ConsultVO>> ccall = cservice.show();
        ccall.enqueue(new Callback<List<ConsultVO>>() {
            @Override
            public void onResponse(Call<List<ConsultVO>> call, Response<List<ConsultVO>> response) {
                clist = response.body();
                Log.d("LOG CLIST", "" + clist);
                for (int i = 0; i < clist.size(); i++) {
                    consultAdapter.add(clist.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<ConsultVO>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_general) {
            makeVisibility(id);
        } else if (id == R.id.nav_mission) {
            makeVisibility(id);

            rv = (RecyclerView) findViewById(R.id.rvmission);
            rv.setLayoutManager(new LinearLayoutManager(this));
            missionAdaper = new MissionAdaper(this);
            rv.setAdapter(missionAdaper);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
            dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
            rv.addItemDecoration(dividerItemDecoration);

            MissionService msv = MissionService.retrofit.create(MissionService.class);
            Call<List<MissionVO>> mcall = msv.showmission(userdata);
            mcall.enqueue(new Callback<List<MissionVO>>() {
                @Override
                public void onResponse(Call<List<MissionVO>> call, Response<List<MissionVO>> response) {
                    mslist = response.body();
                    Log.d("LOG", "" + mslist);
                    for (int i = 0; i < mslist.size(); i++) {
                        missionAdaper.add(mslist.get(i));
                    }
                }

                @Override
                public void onFailure(Call<List<MissionVO>> call, Throwable t) {

                }
            });
        } else if (id == R.id.nav_consult) {
            makeVisibility(id);

            consultAdapter = new ConsultAdapter(MainActivity.this, userdata);
            consultrv = (RecyclerView) findViewById(R.id.rvconsult);
            cLayoutManager = new LinearLayoutManager(this);
            consultrv.setLayoutManager(cLayoutManager);
            consultrv.setAdapter(consultAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL);
            dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
            consultrv.addItemDecoration(dividerItemDecoration);

            ConsultService service = ConsultService.retrofit.create(ConsultService.class);
            Call<List<ConsultVO>> call = service.show();
            call.enqueue(new Callback<List<ConsultVO>>() {
                @Override
                public void onResponse(Call<List<ConsultVO>> call, Response<List<ConsultVO>> response) {
                    clist = response.body();
                    Log.d("LOG CLIST", "" + clist);
                    for (int i = 0; i < clist.size(); i++) {
                        consultAdapter.add(clist.get(i));
                    }
                }

                @Override
                public void onFailure(Call<List<ConsultVO>> call, Throwable t) {

                }
            });

        } else if (id == R.id.nav_manage) {
            makeVisibility(id);

            /*setting_id, setting_name, setting_email*/
            setting_id = (TextView) findViewById(R.id.setting_id);
            setting_name = (TextView) findViewById(R.id.setting_name);
            setting_email = (TextView) findViewById(R.id.setting_email);

            MemberService infoservice = MemberService.retrofit.create(MemberService.class);
            Call<MemberVO> memberVOCall = infoservice.showinfo(userdata);
            memberVOCall.enqueue(new Callback<MemberVO>() {
                @Override
                public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {
                    MemberVO vo = new MemberVO();
                    vo = response.body();

                    setting_id.setText(vo.getMid());
                    setting_name.setText(vo.getMname());
                    setting_email.setText(vo.getMemail());
                    if (vo.getMprofile() == null || vo.getMprofile().equals("null")) {

                    } else {
                        Glide.with(MainActivity.this).load("http://192.168.43.11:8080/member/display?fileName=" + vo.getMprofile()).into(imageView);
                    }

                }

                @Override
                public void onFailure(Call<MemberVO> call, Throwable t) {
                    t.printStackTrace();
                }
            });


            Button setting_btn = (Button) findViewById(R.id.setting_btn);
            setting_btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("-------------", "" + albumURI);
                    if (albumURI == null) {
                        Toast.makeText(MainActivity.this, "프로필 사진을 변경해주세요", Toast.LENGTH_SHORT).show();
                    } else {
                        File file = new File(albumURI.getPath());
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                        MemberVO vo = new MemberVO();
                        vo.setMid(userdata);
                        MemberService updateservice = MemberService.retrofit.create(MemberService.class);
                        Call<ResponseBody> updatecall = updateservice.updateimg(body, vo);
                        updatecall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Toast.makeText(MainActivity.this, "프로필 사진이 변경되었습니다", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                    }
                }
            });

            ImageButton updateimg = (ImageButton) findViewById(R.id.updateimg);
            updateimg.setOnClickListener(new ImageButton.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            doTakePhotoAction();
                        }
                    };

                    DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            doTakeAlbumAction();
                        }
                    };

                    DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    };

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("업로드할 이미지 선택")
                            .setPositiveButton("사진촬영", cameraListener)
                            .setNeutralButton("취소", cancelListener)
                            .setNegativeButton("앨범선택", albumListener)
                            .show();
                }
            });
            checkPermission();

        } else if (id == R.id.nav_father) {
            //아빠 게시판
            makeVisibility(id);
            fatherBoardAdapter = new FatherBoardAdapter(MainActivity.this, userdata);
            fatherboardrv = (RecyclerView) findViewById(R.id.rvfather);
            fbLayoutManager = new LinearLayoutManager(MainActivity.this);
            fatherboardrv.setLayoutManager(fbLayoutManager);
            fatherboardrv.setAdapter(fatherBoardAdapter);

            DividerItemDecoration fdividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
            fdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
            fatherboardrv.addItemDecoration(fdividerItemDecoration);

            FatherBoardService fbservice = FatherBoardService.retrofit.create(FatherBoardService.class);
            Call<List<FatherBoardVO>> fbcall = fbservice.show();
            fbcall.enqueue(new Callback<List<FatherBoardVO>>() {
                @Override
                public void onResponse(Call<List<FatherBoardVO>> call, Response<List<FatherBoardVO>> response) {
                    fblist = response.body();
                    for (int i = 0; i < fblist.size(); i++) {
                        fatherBoardAdapter.add(fblist.get(i));
                    }
                }

                @Override
                public void onFailure(Call<List<FatherBoardVO>> call, Throwable t) {
                    t.printStackTrace();
                }
            });


        } else if (id == R.id.nav_son) {
            //자녀게시판
            makeVisibility(id);
            sonBoardAdapter = new SonBoardAdapter(MainActivity.this, userdata);
            sonboardrv = (RecyclerView) findViewById(R.id.rvson);
            sbLayoutManager = new LinearLayoutManager(MainActivity.this);
            sonboardrv.setLayoutManager(sbLayoutManager);
            sonboardrv.setAdapter(sonBoardAdapter);

            DividerItemDecoration fdividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
            fdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
            sonboardrv.addItemDecoration(fdividerItemDecoration);

            SonBoardService sbservice = SonBoardService.retrofit.create(SonBoardService.class);
            Call<List<SonBoardVO>> sbcall = sbservice.show();
            sbcall.enqueue(new Callback<List<SonBoardVO>>() {
                @Override
                public void onResponse(Call<List<SonBoardVO>> call, Response<List<SonBoardVO>> response) {
                    sblist = response.body();
                    for (int i = 0; i < sblist.size(); i++) {
                        sonBoardAdapter.add(sblist.get(i));
                    }
                }

                @Override
                public void onFailure(Call<List<SonBoardVO>> call, Throwable t) {
                    t.printStackTrace();
                }
            });

        } else if (id == R.id.nav_friend) {
            //친구목록 작업
            makeVisibility(id);
            falistrv = (RecyclerView) findViewById(R.id.parentlistrv);
            falistrv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            fatherListAdapter = new FatherListAdapter(MainActivity.this);
            falistrv.setAdapter(fatherListAdapter);

            DividerItemDecoration fdividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
            fdividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
            falistrv.addItemDecoration(fdividerItemDecoration);

            MemberService mymnoservice = MemberService.retrofit.create(MemberService.class);
            Call<ResponseBody> mymnocall = mymnoservice.showmno(userdata);
            mymnocall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        mynumber = Integer.parseInt(response.body().string());

                        SFatherService sFatherService = SFatherService.retrofit.create(SFatherService.class);
                        Call<List<SFatherVO>> sfacall = sFatherService.showfather(mynumber);
                        sfacall.enqueue(new Callback<List<SFatherVO>>() {
                            @Override
                            public void onResponse(Call<List<SFatherVO>> call, Response<List<SFatherVO>> response) {
                                sfalist = response.body();
                                Log.d("LOG FATHER", "" + sfalist);
                                for (int i = 0; i < sfalist.size(); i++) {
                                    fatherListAdapter.add(sfalist.get(i));
                                }
                            }

                            @Override
                            public void onFailure(Call<List<SFatherVO>> call, Throwable t) {

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });


            flistrv = (RecyclerView) findViewById(R.id.friendrv);
            flistrv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            friendListAdapter = new FriendListAdapter(MainActivity.this);
            flistrv.setAdapter(friendListAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL);
            dividerItemDecoration.setDrawable(getApplicationContext().getResources().getDrawable(R.drawable.custom_border));
            flistrv.addItemDecoration(dividerItemDecoration);

            MemberService mnoservice = MemberService.retrofit.create(MemberService.class);
            Call<ResponseBody> mnocall = mnoservice.showmno(userdata);
            mnocall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        mynumber = Integer.parseInt(response.body().string());

                        SFriendService sFriendService = SFriendService.retrofit.create(SFriendService.class);
                        Call<List<SFriendVO>> sfcall = sFriendService.showfriend(mynumber);
                        sfcall.enqueue(new Callback<List<SFriendVO>>() {
                            @Override
                            public void onResponse(Call<List<SFriendVO>> call, Response<List<SFriendVO>> response) {
                                sflist = response.body();
                                for (int i = 0; i < sflist.size(); i++) {
                                    friendListAdapter.add(sflist.get(i));
                                }
                            }

                            @Override
                            public void onFailure(Call<List<SFriendVO>> call, Throwable t) {

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });


        } else if (id == R.id.nav_friend_add) {
            //친구추가 작업
            makeVisibility(id);
            father_add_id.setText("");
            friend_add_id.setText("");
            final Button father_add_btn = (Button) findViewById(R.id.father_add_btn);
            father_add_btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (father_add_id.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "ID를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else if (father_add_id.getText().toString().equals(userdata)) {
                        Toast.makeText(MainActivity.this, "자신의 ID는 추가등록 할 수 없습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        MemberService fatherCheckService = MemberService.retrofit.create(MemberService.class);
                        Call<ResponseBody> fatherCheckCall = fatherCheckService.idcheck(father_add_id.getText().toString());
                        fatherCheckCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String check = response.body().string();
                                    if (check.equals("null")) {
                                        Toast.makeText(MainActivity.this, "ID를 제대로 입력해주세요", Toast.LENGTH_SHORT).show();
                                    } else {
                                        MemberService fatherService = MemberService.retrofit.create(MemberService.class);
                                        Call<ResponseBody> fatherCall = fatherService.showmno(userdata);
                                        fatherCall.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                try {
                                                    mno = Integer.parseInt(response.body().string());
                                                    MemberService fanoService = MemberService.retrofit.create(MemberService.class);
                                                    Call<ResponseBody> fanoCall = fanoService.showmno(father_add_id.getText().toString());
                                                    fanoCall.enqueue(new Callback<ResponseBody>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                            try {
                                                                fano = Integer.parseInt(response.body().string());
                                                                FatherVO vo = new FatherVO();
                                                                vo.setMno(mno);
                                                                vo.setFano(fano);

                                                                FatherService fatherService = FatherService.retrofit.create(FatherService.class);
                                                                Call<ResponseBody> faCall = fatherService.fatheradd(vo);
                                                                faCall.enqueue(new Callback<ResponseBody>() {
                                                                    @Override
                                                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                                        Toast.makeText(MainActivity.this, "추가되었습니다", Toast.LENGTH_SHORT).show();
                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                                        t.printStackTrace();
                                                                    }
                                                                });

                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                            t.printStackTrace();
                                                        }
                                                    });

                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                t.printStackTrace();
                                            }
                                        });
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
                    }
                }
            });

            Button friend_add_btn = (Button) findViewById(R.id.friend_add_btn);
            friend_add_btn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (friend_add_id.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "ID를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else if (friend_add_id.getText().toString().equals(userdata)) {
                        Toast.makeText(MainActivity.this, "자신의 ID는 친구등록을 할 수 없습니다", Toast.LENGTH_SHORT).show();
                    } else {
                        MemberService fcheckservice = MemberService.retrofit.create(MemberService.class);
                        Call<ResponseBody> fcheckcall = fcheckservice.idcheck(friend_add_id.getText().toString());
                        fcheckcall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String check = response.body().string();
                                    if (check.equals("null")) {
                                        Toast.makeText(MainActivity.this, "친구 ID를 제대로 입력해주세요", Toast.LENGTH_SHORT).show();
                                    } else {
                                        MemberService fservice = MemberService.retrofit.create(MemberService.class);
                                        Call<ResponseBody> fcall = fservice.showmno(userdata);
                                        fcall.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                try {
                                                    mno = Integer.parseInt(response.body().string());

                                                    MemberService fnoservice = MemberService.retrofit.create(MemberService.class);
                                                    Call<ResponseBody> fnocall = fnoservice.showmno(friend_add_id.getText().toString());
                                                    fnocall.enqueue(new Callback<ResponseBody>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                            try {
                                                                fno = Integer.parseInt(response.body().string());
                                                                Log.d("LOG MNO :", "" + mno);
                                                                Log.d("LOG FNO :", "" + fno);
                                                                FriendVO vo = new FriendVO();
                                                                vo.setMno(mno);
                                                                vo.setFno(fno);
                                                                FriendService faddservice = FriendService.retrofit.create(FriendService.class);
                                                                Call<ResponseBody> faddcall = faddservice.friendadd(vo);
                                                                faddcall.enqueue(new Callback<ResponseBody>() {
                                                                    @Override
                                                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                                        Toast.makeText(MainActivity.this, "친구추가 완료", Toast.LENGTH_SHORT).show();
                                                                    }

                                                                    @Override
                                                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                                        t.printStackTrace();
                                                                    }
                                                                });

                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                            t.printStackTrace();
                                                        }
                                                    });


                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                t.printStackTrace();
                                            }
                                        });
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });


                    }
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Log.i("REQUEST_TAKE_PHOTO", "OK");
                        cropSingleImage(imageUri);

                    } catch (Exception e) {
                        Log.e("REQUEST_TA KE_PHOTO", e.toString());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "사진찍기를 취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQUEST_TAKE_ALBUM:
                if (resultCode == Activity.RESULT_OK) {

                    if (data.getData() != null) {
                        try {
                            File albumFile = null;
                            albumFile = createImageFile();
                            photoURI = data.getData();
                            albumURI = Uri.fromFile(albumFile);
                            cropImage();
                        } catch (Exception e) {
                            Log.e("TAKE_ALBUM_SINGLE ERROR", e.toString());
                        }
                    }
                }
                break;

            case REQUEST_IMAGE_CROP:
                if (resultCode == Activity.RESULT_OK) {

                    galleryAddPic();
                    imageView.setImageURI(albumURI);

                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_CAMERA:
                for (int i = 0; i < grantResults.length; i++) {
                    // grantResults[] : 허용된 권한은 0, 거부한 권한은 -1
                    if (grantResults[i] < 0) {
                        Toast.makeText(MainActivity.this, "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                // 허용했다면 이 부분에서..

                break;
        }
    }

    public View makeVisibility(int id) {

        Iterator<Integer> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            Integer key = iterator.next();
            View value = map.get(key);
            value.setVisibility(View.GONE);
        }

        View vid = map.get(id);
        vid.setVisibility(View.VISIBLE);

        return vid;
    }

    public void doTakePhotoAction() {
        String state = Environment.getExternalStorageState();
        // 외장 메모리 검사
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    Log.e("captureCamera Error", ex.toString());
                }

                if (photoFile != null) {
                    // getUriForFile의 두 번째 인자는 Manifest provier의 authorites와 일치해야 함

                    Uri providerURI = FileProvider.getUriForFile(this, getPackageName(), photoFile);
                    imageUri = providerURI;

                    // 인텐트에 전달할 때는 FileProvier의 Return값인 content://로만!!, providerURI의 값에 카메라 데이터를 넣어 보냄
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerURI);

                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        } else {
            Toast.makeText(this, "저장공간이 접근 불가능한 기기입니다", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";
        File imageFile = null;
        File storageDir = new File(Environment.getExternalStorageDirectory() + "/Pictures", "faniddo");

        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdirs();
        }

        imageFile = new File(storageDir, imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        return imageFile;
    }

    public void doTakeAlbumAction() {
        Log.i("getAlbum", "Call");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, REQUEST_TAKE_ALBUM);
    }

    private void galleryAddPic() {
        Log.i("galleryAddPic", "Call");
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // 해당 경로에 있는 파일을 객체화(새로 파일을 만든다는 것으로 이해하면 안 됨)
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
        Toast.makeText(this, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public void cropImage() {
        Log.i("cropImage", "Call");
        Log.i("cropImage", "photoURI : " + photoURI + " / albumURI : " + albumURI);

        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        // 50x50픽셀미만은 편집할 수 없다는 문구 처리 + 갤러리, 포토 둘다 호환하는 방법
        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropIntent.setDataAndType(photoURI, "image/*");
        //cropIntent.putExtra("outputX", 200); // crop한 이미지의 x축 크기, 결과물의 크기
        //cropIntent.putExtra("outputY", 200); // crop한 이미지의 y축 크기
        cropIntent.putExtra("aspectX", 1); // crop 박스의 x축 비율, 1&1이면 정사각형
        cropIntent.putExtra("aspectY", 1); // crop 박스의 y축 비율
        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("output", albumURI); // 크랍된 이미지를 해당 경로에 저장
        startActivityForResult(cropIntent, REQUEST_IMAGE_CROP);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 처음 호출시엔 if()안의 부분은 false로 리턴 됨 -> else{..}의 요청으로 넘어감
            if ((ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) ||
                    (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))) {
                new AlertDialog.Builder(this)
                        .setTitle("알림")
                        .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                        .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + getPackageName()));
                                startActivity(intent);
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, MY_PERMISSION_CAMERA);
            }
        }
    }

    public void cropSingleImage(Uri photoUriPath) {
        Log.i("cropSingleImage", "Call");
        Log.i("cropSingleImage", "photoUriPath : " + photoUriPath);

        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        // 50x50픽셀미만은 편집할 수 없다는 문구 처리 + 갤러리, 포토 둘다 호환하는 방법, addFlags로도 에러 나서 setFlags
        // 누가 버전 처리방법임
        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        cropIntent.setDataAndType(photoUriPath, "image/*");
        cropIntent.putExtra("aspectX", 1); // crop 박스의 x축 비율, 1&1이면 정사각형
        cropIntent.putExtra("aspectY", 1); // crop 박스의 y축 비율
        Log.i("cropSingleImage", "photoUriPath22 : " + photoUriPath);

        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("output", photoUriPath); // 크랍된 이미지를 해당 경로에 저장

        albumURI = photoUriPath;

        List<ResolveInfo> list = getPackageManager().queryIntentActivities(cropIntent, 0);
        grantUriPermission(list.get(0).activityInfo.packageName, photoUriPath, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Intent i = new Intent(cropIntent);

        ResolveInfo res = list.get(0);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        grantUriPermission(res.activityInfo.packageName, photoUriPath,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));

        startActivityForResult(i, REQUEST_IMAGE_CROP);
    }
}
