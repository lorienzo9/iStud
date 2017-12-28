package com.aveteam.lorienzo9.istudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aveteam.lorienzo9.istudy.ViewPagerAdapters.ViewPagerAdapter;

/**
 * Created by lorienzo9 on 19/12/17.
 */
//Potrei implementare un array di layout da creare e gonfiarli nell'adapter come UN Fragmentstareadapter
public class SignUpPager extends AppCompatActivity {
    public ViewPager viewpager;
    int NUM_PAGES = 4;
    int distance;
    private LinearLayout pager_indicator;
    private ImageView[] dots;
    int oldPosition = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activty_pager);
        pager_indicator = (LinearLayout)findViewById(R.id.viewPagerCountDots);
        setUpDots();
        viewpager = (ViewPager)findViewById(R.id.signup_pager);

        viewpager.setAdapter(new ViewPagerAdapter(NUM_PAGES, getSupportFragmentManager()));
        //viewpager.setCurrentItem(0);
        viewpager.setCurrentItem(0);


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position > oldPosition){ //lo faccio solo con scroll incrementativo
                    dots[position].setImageDrawable(getResources().getDrawable(R.drawable.dot_selected)); //Coloro tutti quelli selezionati
                }else if(position < oldPosition){
                    dots[position+1].setImageDrawable(getResources().getDrawable(R.drawable.dot_unselected));
                }
                oldPosition = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void setUpDots(){

        dots = new ImageView[NUM_PAGES];

        for (int i = 0; i<NUM_PAGES; i++){ //creo i punti come se fossero NONSELEZIONATI
            dots[i] = new ImageView(getApplicationContext());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.dot_unselected));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            distance = (int)(pager_indicator.getY())/10;
            params.setMargins(0, distance, 0, distance);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.dot_selected)); //Il primo punto è SELEZIONATO
    }

    //Modifica questo
    //https://www.androidhive.info/2016/05/android-build-intro-slider-app/  è interessante

    int layout[]; //Inserisci i vari layout da visualizzare
    // sarà un casino gestire i vari Intent
    public class ViewPagerAdapterSignUp extends PagerAdapter{
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(layout[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layout.length;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

    }

}

