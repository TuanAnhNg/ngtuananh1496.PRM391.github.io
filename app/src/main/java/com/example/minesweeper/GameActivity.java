package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.minesweeper.model.Bomb;
import com.example.minesweeper.model.GameButton;
import com.example.minesweeper.model.GameMode;
import com.example.minesweeper.model.ListGameButton;
import com.example.minesweeper.model.ZoomLinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private final int easyBombNumber = 9;
    private final int mediumBombNumber = 16;
    private final int expertBombNumber = 99;
    private int[] gameSize; //gameSize[0]: x, gameSize[1]: y, gameSize[2]: number of bomb
    private String gameMode = "";
    private ImageButton imageButton;
    boolean buttonFlag = true;
    private LinearLayout gameLayout;
    private List<Bomb> bombs;
    private ListGameButton listGameButton;
    private final ZoomLinearLayout zoomLinearLayout = findViewById(R.id.zoom_linear_layout);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        zoomLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                zoomLinearLayout.init(GameActivity.this);
                return false;
            }
        });
        imageButton = findViewById(R.id.imgBtnStatus);
        gameLayout = findViewById(R.id.gameLayout);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gameMode = bundle.getString("gameMode");
            switch (gameMode.trim()) {
                case GameMode.EASY:
                    gameSize = new int[]{9, 9, easyBombNumber};
                    break;
                case GameMode.MEDIUM:
                    gameSize = new int[]{14, 14, mediumBombNumber};
                    break;
                case GameMode.EXPERT:
                    gameSize = new int[]{30, 16, expertBombNumber};
                    break;
            }
        }
        addBomb(gameSize);
        listGameButton = new ListGameButton(bombs);
        createGame();
    }

    public void statusChange(View view) {
        if (!buttonFlag) {
            buttonFlag = true;
            imageButton.setImageResource(R.drawable.flagged);
        } else {
            buttonFlag = false;
            imageButton.setImageResource(R.drawable.bomb);
        }
    }

    public void newGame(View view) {
        buttonFlag = true;
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void createGame() {
        for (int i = 0; i < gameSize[1]; i++) {
            LinearLayout linearLayout = new LinearLayout(GameActivity.this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(0,0,0,0);
            for (int j = 0; j < gameSize[0]; j++) {
                GameButton gameButton = new GameButton(this);
                gameButton.setCoordinateX(j);
                gameButton.setCoordinateY(i);
                gameButton.setLayoutParams(layoutParams);
                gameButton.setImageResource(R.drawable.facing_down);
                gameButton.setBackgroundColor(000000);
                int value = listGameButton.addGameButton(gameButton);
                gameButton.setValue(value);
                linearLayout.addView(gameButton);
            }
            gameLayout.addView(linearLayout);
        }
    }

    private void addBomb(int[] gameSize) {
        bombs = new ArrayList<>();
//        Random coordinateXRandom = new Random();
//        Random coordinateYRandom = new Random();
        for (int i = 0; i < gameSize[2]; i++) {
            Random random = new Random();
            int x = random.nextInt(gameSize[0]);
            int y = random.nextInt(gameSize[1]);
            Bomb bomb = new Bomb(x, y);
            for (Bomb b: bombs){
                if(b.compare(bomb)){
                    i--;
                    continue;
                }
            }
            bombs.add(bomb);
        }
    }

}