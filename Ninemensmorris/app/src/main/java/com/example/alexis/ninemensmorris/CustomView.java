package com.example.alexis.ninemensmorris;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomView extends View{

    private int radius;
    private final int RED = 0;
    private final int GREEN = 1;
    private final int fly_limit = 3;
    private final int max_red_pawns = 9;
    private final int max_green_pawns = 9;


    private int red_pawns;
    private int green_pawns;
    private int turn;
    private boolean place_item;
    private boolean remove_item;
    private boolean trans_place_move;
    private boolean select_item;
    private boolean move;
    private boolean fly_green;
    private boolean fly_red;
    private boolean win;
    private int current_red_pawns = 0;
    private int current_green_pawns = 0;


    private TextView tv_turn;
    private TextView tv_score;

    private BoardButton selected_element;

    private Rect mRect;
    private Rect mRect2;
    private Rect mRect3;

    private List<BoardButton> lbb;
    private List<Triple<BoardButton,BoardButton,BoardButton>> combination;
    private Paint board_paint;

    private SpannableStringBuilder builder;

    public CustomView(Context c) {
        super(c);
        init();
    }

    public CustomView(Context c, AttributeSet as) {
        super(c, as);
        init();
    }
    public CustomView(Context c, AttributeSet as, int default_style) {
        super(c, as, default_style);
        init();
    }
    private void init() {
        int i = 0;
        place_item = true;
        remove_item = false;
        move = false;
        fly_green = false;
        fly_red = false;
        trans_place_move = false;
        select_item = false;
        turn = RED;
        red_pawns = max_red_pawns;
        green_pawns = max_green_pawns;


        board_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        board_paint.setColor(Color.rgb(160,82,60));
        board_paint.setStyle(Paint.Style.STROKE);

        builder = new SpannableStringBuilder();

        mRect = new Rect();
        mRect2 = new Rect();
        mRect3 = new Rect();

        combination = new ArrayList<>();
        lbb = new ArrayList<>();
        while(i < 24) {
            lbb.add(new BoardButton(0, 0));
            i++;
        }
        combination.add(new Triple<>(lbb.get(0),lbb.get(1),lbb.get(2)));
        combination.add(new Triple<>(lbb.get(0),lbb.get(7),lbb.get(6)));
        combination.add(new Triple<>(lbb.get(2),lbb.get(3),lbb.get(4)));
        combination.add(new Triple<>(lbb.get(4),lbb.get(5),lbb.get(6)));
        combination.add(new Triple<>(lbb.get(8),lbb.get(9),lbb.get(10)));
        combination.add(new Triple<>(lbb.get(8),lbb.get(15),lbb.get(14)));
        combination.add(new Triple<>(lbb.get(10),lbb.get(11),lbb.get(12)));
        combination.add(new Triple<>(lbb.get(12),lbb.get(13),lbb.get(14)));
        combination.add(new Triple<>(lbb.get(16),lbb.get(17),lbb.get(18)));
        combination.add(new Triple<>(lbb.get(16),lbb.get(23),lbb.get(22)));
        combination.add(new Triple<>(lbb.get(18),lbb.get(19),lbb.get(20)));
        combination.add(new Triple<>(lbb.get(20),lbb.get(21),lbb.get(22)));
        combination.add(new Triple<>(lbb.get(1),lbb.get(9),lbb.get(17)));
        combination.add(new Triple<>(lbb.get(3),lbb.get(11),lbb.get(19)));
        combination.add(new Triple<>(lbb.get(5),lbb.get(13),lbb.get(21)));
        combination.add(new Triple<>(lbb.get(7),lbb.get(15),lbb.get(23)));


    }

    public void onDraw(Canvas canvas) {


        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int size = Math.min(width,height);
        int margin_top = height/15;
        int margin_size = width/15;
        radius = size/40;


        mRect.set(margin_size,margin_top,size - margin_size,size - margin_top);
        canvas.drawRect(mRect,board_paint);

        mRect2.set(size/8 + margin_size,size/8 + margin_top,7*size/8 - margin_size,7 * size/8 - margin_top);
        canvas.drawRect(mRect2,board_paint);

        mRect3.set(size/4 + margin_size,size/4 + margin_top,3 * size/4 - margin_size,3 * size/4 - margin_top);
        canvas.drawRect(mRect3,board_paint);


        canvas.drawLine(margin_size,mRect3.centerY(),size/4 + margin_size,mRect3.centerY(),board_paint);
        canvas.drawLine(mRect3.centerX(),size - margin_top,mRect3.centerX(),3 * size/4 - margin_top,board_paint);
        canvas.drawLine(size - margin_size,mRect3.centerY(),3 * size/4 - margin_size,mRect3.centerY(),board_paint);
        canvas.drawLine(mRect3.centerX(),margin_top,mRect3.centerX(),size/4 + margin_top,board_paint);


        lbb.get(0).setPosition(margin_size,margin_top);
        canvas.drawCircle(lbb.get(0).getPosx(),lbb.get(0).getPosy(),radius,lbb.get(0).getPaint());

        lbb.get(1).setPosition(margin_size,mRect.centerY());
        canvas.drawCircle(lbb.get(1).getPosx(),lbb.get(1).getPosy(),radius,lbb.get(1).getPaint());
        lbb.get(2).setPosition(margin_size,size - margin_top);
        canvas.drawCircle(lbb.get(2).getPosx(),lbb.get(2).getPosy(),radius,lbb.get(2).getPaint());
        lbb.get(3).setPosition(mRect.centerX(),size - margin_top);
        canvas.drawCircle(lbb.get(3).getPosx(),lbb.get(3).getPosy(),radius,lbb.get(3).getPaint());
        lbb.get(4).setPosition(size - margin_size,size - margin_top);
        canvas.drawCircle(lbb.get(4).getPosx(),lbb.get(4).getPosy(),radius,lbb.get(4).getPaint());
        lbb.get(5).setPosition(size - margin_size,mRect.centerY());
        canvas.drawCircle(lbb.get(5).getPosx(),lbb.get(5).getPosy(),radius,lbb.get(5).getPaint());
        lbb.get(6).setPosition(size - margin_size,margin_top);
        canvas.drawCircle(lbb.get(6).getPosx(),lbb.get(6).getPosy(),radius,lbb.get(6).getPaint());
        lbb.get(7).setPosition(mRect.centerX(),margin_top);
        canvas.drawCircle(lbb.get(7).getPosx(),lbb.get(7).getPosy(),radius,lbb.get(7).getPaint());


        lbb.get(8).setPosition(size/8 + margin_size,size/8 + margin_top);
        canvas.drawCircle(lbb.get(8).getPosx(),lbb.get(8).getPosy(),radius,lbb.get(8).getPaint());
        lbb.get(9).setPosition(size/8 + margin_size,mRect2.centerY());
        canvas.drawCircle(lbb.get(9).getPosx(),lbb.get(9).getPosy(),radius,lbb.get(9).getPaint());
        lbb.get(10).setPosition(size/8 + margin_size,7 * size/8 - margin_top);
        canvas.drawCircle(lbb.get(10).getPosx(),lbb.get(10).getPosy(),radius,lbb.get(10).getPaint());
        lbb.get(11).setPosition(mRect2.centerX(), 7 * size/8 - margin_top);
        canvas.drawCircle(lbb.get(11).getPosx(),lbb.get(11).getPosy(),radius,lbb.get(11).getPaint());
        lbb.get(12).setPosition(7*size/8 - margin_size,7 * size/8 - margin_top);
        canvas.drawCircle(lbb.get(12).getPosx(),lbb.get(12).getPosy(),radius,lbb.get(12).getPaint());
        lbb.get(13).setPosition(7*size/8 - margin_size,mRect2.centerY());
        canvas.drawCircle(lbb.get(13).getPosx(),lbb.get(13).getPosy(),radius,lbb.get(13).getPaint());
        lbb.get(14).setPosition(7*size/8 - margin_size,size/8 + margin_top);
        canvas.drawCircle(lbb.get(14).getPosx(),lbb.get(14).getPosy(),radius,lbb.get(14).getPaint());
        lbb.get(15).setPosition(mRect2.centerX(),size/8 + margin_top);
        canvas.drawCircle(lbb.get(15).getPosx(),lbb.get(15).getPosy(),radius,lbb.get(15).getPaint());



        lbb.get(16).setPosition(size/4 + margin_size,size/4 + margin_top);
        canvas.drawCircle(lbb.get(16).getPosx(),lbb.get(16).getPosy(),radius,lbb.get(16).getPaint());
        lbb.get(17).setPosition(size/4 + margin_size,mRect3.centerY());
        canvas.drawCircle(lbb.get(17).getPosx(),lbb.get(17).getPosy(),radius,lbb.get(17).getPaint());
        lbb.get(18).setPosition(size/4 + margin_size,3 * size/4 - margin_top);
        canvas.drawCircle(lbb.get(18).getPosx(),lbb.get(18).getPosy(),radius,lbb.get(18).getPaint());
        lbb.get(19).setPosition(mRect3.centerX(),3 * size/4 - margin_top);
        canvas.drawCircle(lbb.get(19).getPosx(),lbb.get(19).getPosy(),radius,lbb.get(19).getPaint());
        lbb.get(20).setPosition(3 * size/4 - margin_size,3 * size/4 - margin_top);
        canvas.drawCircle(lbb.get(20).getPosx(),lbb.get(20).getPosy(),radius,lbb.get(20).getPaint());
        lbb.get(21).setPosition(3 * size/4 - margin_size,mRect3.centerY());
        canvas.drawCircle(lbb.get(21).getPosx(),lbb.get(21).getPosy(),radius,lbb.get(21).getPaint());
        lbb.get(22).setPosition(3 * size/4 - margin_size,size/4 + margin_top);
        canvas.drawCircle(lbb.get(22).getPosx(),lbb.get(22).getPosy(),radius,lbb.get(22).getPaint());
        lbb.get(23).setPosition(mRect3.centerX(),size/4 + margin_top);
        canvas.drawCircle(lbb.get(23).getPosx(),lbb.get(23).getPosy(),radius,lbb.get(23).getPaint());

        super.onDraw(canvas);
    }


    public boolean onTouchEvent(MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                if (place_item)
                    placeitem(event.getX(), event.getY());
                if (remove_item)
                    remove_item(event.getX(), event.getY());
                if (select_item)
                    selectitem(event.getX(), event.getY());
                if (move)
                    move_item(event.getX(), event.getY());
        }
        return super.onTouchEvent(event);
    }

    private void move_item(float x, float y) {
        for (BoardButton element : lbb) {
            if ((element.getPosx() - radius <= x && x <= element.getPosx() + radius) &&
                    ((element.getPosy() - radius <= y && y <= element.getPosy() + radius))) {
                if (element.getPaint().getColor() == Color.BLUE) {
                    element.setEmpty(false);
                    element.getPaint().setColor(selected_element.getPaint().getColor());
                    selected_element.setEmpty(true);
                    selected_element.getPaint().setColor(Color.DKGRAY);
                    turn = (turn + 1) % 2;
                    move = false;
                    all_empty(Color.DKGRAY);
                    if (check_mill(element)) {
                        remove_item = true;
                        turn = (turn + 1) % 2;
                    } else {
                        select_item = true;
                    }
                }
            }
        }
        if (no_move()) {
            win = true;
            place_item = false;
            remove_item = false;
            move = false;
            fly_green = false;
            fly_red = false;
            trans_place_move = false;
            select_item = false;

        }
        invalidate();
        ChangeText();

    }


    private void selectitem(float x, float y) {
            for (BoardButton element : lbb) {
                if ((element.getPosx() - radius <= x && x <= element.getPosx() + radius) &&
                        ((element.getPosy() - radius <= y && y <= element.getPosy() + radius))) {

                    if (turn == RED && element.getPaint().getColor() == Color.RED) {
                        selected_element = element;
                        select_item = false;
                        move = true;
                        if (fly_red)
                            all_empty(Color.BLUE);
                        else
                            change_neighbour(element, Color.BLUE);
                    } else if (turn == GREEN && element.getPaint().getColor() == Color.GREEN) {
                        selected_element = element;
                        select_item = false;
                        move = true;
                        if (fly_green)
                            all_empty(Color.BLUE);
                        else
                            change_neighbour(element, Color.BLUE);
                    }
                }
            }
        //}
        invalidate();
        ChangeText();
    }

    private boolean no_move() {
        int color;
        if(turn == RED && !fly_red)
            color = Color.RED;
        else if(turn == GREEN && !fly_green)
            color = Color.GREEN;
        else
            return false;
        for(Triple<BoardButton,BoardButton,BoardButton> element : combination)
        {
         if(element.first.getPaint().getColor() == color)
             if(element.second.isEmpty())
                 return false;
            if(element.second.getPaint().getColor() == color)
                if(element.first.isEmpty() || element.third.isEmpty())
                    return false;
            if(element.third.getPaint().getColor() == color)
                if(element.second.isEmpty())
                    return false;
        }
        return true;
    }

    private void all_empty(int blue) {
        for (BoardButton element : lbb) {
            if (element.isEmpty())
                element.getPaint().setColor(blue);
        }
        }

    private void remove_item(float x, float y) {

        for (BoardButton element : lbb) {
            if ((element.getPosx() - radius <= x && x <= element.getPosx() + radius) &&
                    ((element.getPosy() - radius <= y && y <= element.getPosy() + radius))) {
                if (!check_mill(element) || all_check_mill()) {
                    if (turn == RED && element.getPaint().getColor() == Color.GREEN) {
                        element.getPaint().setColor(Color.DKGRAY);
                        element.setEmpty(true);
                        green_pawns--;
                        current_green_pawns--;
                        if (current_green_pawns == fly_limit && trans_place_move) {
                            fly_green = true;
                        }
                        remove_item = false;
                        turn = (turn + 1)%2;
                        if (trans_place_move)
                            select_item = true;
                        else
                            place_item = true;
                    } else if (turn == GREEN && element.getPaint().getColor() == Color.RED) {
                        element.getPaint().setColor(Color.DKGRAY);
                        element.setEmpty(true);
                        red_pawns--;
                        current_red_pawns--;
                        if (current_red_pawns == fly_limit && trans_place_move) {
                            fly_red = true;
                        }
                        remove_item = false;
                        turn = (turn + 1)%2;
                        if (trans_place_move)
                            select_item = true;
                        else
                            place_item = true;
                    }

                }
            }
        }
        if(winner())
        {
            win = true;
            place_item = false;
            remove_item = false;
            move = false;
            fly_green = false;
            fly_red = false;
            trans_place_move = false;
            select_item = false;
        }
        ChangeText();
        invalidate();
        UpdateScore();
    }

    private void placeitem(float x, float y)
    {
        for (BoardButton element : lbb) {
            if ((element.getPosx() - radius <= x && x <= element.getPosx() + radius) &&
                    ((element.getPosy() - radius <= y && y <= element.getPosy() + radius))) {
                if (element.isEmpty()) {
                    if (turn == RED) {
                        element.getPaint().setColor(Color.RED);
                        current_red_pawns++;
                        turn = GREEN;

                    } else {
                        element.getPaint().setColor(Color.GREEN);
                        current_green_pawns++;
                        turn = RED;
                    }
                    element.setEmpty(false);


                    if (check_mill(element)) {
                        remove_item = true;
                        place_item = false;
                        if(turn == RED)
                            turn = GREEN;
                        else
                            turn = RED;
                    }
                        if (current_red_pawns == red_pawns && current_green_pawns == green_pawns) {
                        place_item = false;
                        trans_place_move = true;
                        select_item = true;
                    }
                }
            }
        }
        invalidate();
        ChangeText();
        UpdateScore();
    }

    private boolean check_mill(BoardButton bb) {

        int color = bb.getPaint().getColor();
        Iterator<Triple<BoardButton,BoardButton,BoardButton>> it = combination.iterator();
        while(it.hasNext())
        {
            Triple<BoardButton,BoardButton,BoardButton> triple = it.next();
            BoardButton el1 = triple.first;
            BoardButton el2 = triple.second;
            BoardButton el3 = triple.third;

            if(bb.getPosx() == el1.getPosx() && bb.getPosy() == el1.getPosy())
            {
                if(el2.getPaint().getColor() == color && el3.getPaint().getColor() == color)
                {
                    return true;
                }

            }
            else if(bb.getPosx() == el2.getPosx() && bb.getPosy() == el2.getPosy())
            {

                if(el1.getPaint().getColor() == color && el3.getPaint().getColor() == color)
                {
                   return true;
                }

            }
            else if(bb.getPosx() == el3.getPosx() && bb.getPosy() == el3.getPosy())
            {
                if(el2.getPaint().getColor() == color && el1.getPaint().getColor() == color)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean all_check_mill()
    {
        int color;
        int pawns;
        if(turn == RED) {
            color = Color.RED;
            pawns = current_red_pawns;
        }
        else {
            color = Color.GREEN;
            pawns = current_green_pawns;
        }
        int i = 0;
        for(BoardButton element : lbb)
        {
            if(element.getPaint().getColor() == color && check_mill(element))
                i++;
        }

        if(i == pawns)
            return true;
        return false;
    }

    public void UpdateScore()
    {
        builder.clear();

        String red = getResources().getString(R.string.red);
        String green = getResources().getString(R.string.green);

        SpannableString redSpannable= new SpannableString(red);
        SpannableString greenSpannable = new SpannableString(green);

        redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, red.length(), 0);
        greenSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, green.length(), 0);

        builder.append(redSpannable);
        builder.append(" ");
        builder.append(String.valueOf(current_red_pawns));
        builder.append(" - ");
        builder.append(String.valueOf(current_green_pawns));
        builder.append(" ");
        builder.append(greenSpannable);
        tv_score.setText(builder,TextView.BufferType.SPANNABLE);
    }

    public void ChangeText() {
        builder.clear();
        String red = getResources().getString(R.string.red);
        String green = getResources().getString(R.string.green);

        SpannableString redSpannable= new SpannableString(red);
        SpannableString greenSpannable = new SpannableString(green);

        redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, red.length(), 0);
        greenSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, green.length(), 0);

        Resources resources = getResources();

        if (turn == RED && place_item)
        {
            builder.append(redSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.insert));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if (turn == RED && select_item) {

            builder.append(redSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.select));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if (turn == RED && move) {
            builder.append(redSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.move));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if (turn == RED && remove_item) {
            System.out.println("Test Red");
            builder.append(redSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.remove,greenSpannable));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if(turn == RED && (win || no_move()))
        {
            builder.append(greenSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.winner));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);

        }
        else if (turn == GREEN && place_item) {

            builder.append(greenSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.insert));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if (turn == GREEN && select_item) {
            builder.append(greenSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.select));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if (turn == GREEN && move) {

            builder.append(greenSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.move));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if (turn == GREEN && remove_item) {
            System.out.println("Test Green");
            builder.append(greenSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.remove,redSpannable));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);
        }
        else if(turn == GREEN && (win ||no_move()))
        {
            builder.append(redSpannable);
            builder.append(", ");
            builder.append(resources.getString(R.string.winner));
            tv_turn.setText(builder, TextView.BufferType.SPANNABLE);

        }
    }

    public void setTv(TextView tv,TextView score)
    {
        tv_turn = tv;
        tv_score = score;
    }


    public void change_neighbour(BoardButton bb, int color)
    {

        for (Triple<BoardButton, BoardButton, BoardButton> triple : combination) {
            BoardButton el1 = triple.first;
            BoardButton el2 = triple.second;
            BoardButton el3 = triple.third;


            if (bb.getPosx() == el1.getPosx() && bb.getPosy() == el1.getPosy() ||
                    bb.getPosx() == el3.getPosx() && bb.getPosy() == el3.getPosy()) {
                if (el2.isEmpty())
                    el2.getPaint().setColor(color);
            }
            if (bb.getPosx() == el2.getPosx() && bb.getPosy() == el2.getPosy()) {
                if (el1.isEmpty())
                    el1.getPaint().setColor(color);
                if (el3.isEmpty())
                    el3.getPaint().setColor(color);
            }

        }
    }

    public void reset()
    {
        place_item = true;
        remove_item = false;
        move = false;
        fly_green = false;
        fly_red = false;
        trans_place_move = false;
        select_item = false;
        current_green_pawns = 0;
        current_red_pawns = 0;
        red_pawns = max_red_pawns;
        green_pawns = max_green_pawns;
        turn = RED;
        for(BoardButton element : lbb)
        {
            element.getPaint().setColor(Color.DKGRAY);
            element.setEmpty(true);
        }
        invalidate();
        ChangeText();
        UpdateScore();
    }


    private boolean winner()
    {
        return trans_place_move && (current_green_pawns == 2 || current_red_pawns == 2);

}}