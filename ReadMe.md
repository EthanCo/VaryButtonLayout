##VaryButtonLayout
- 具有多种状态的Layout，内部的Button的形态具有高自由度
	- VaryButtonLayout只进行下一个状态的逻辑处理及实现button的点击回调

![VaryButtonLayout](http://img-storage.qiniudn.com/15-11-9/91983899.jpg)

### 使用XML添加状态 ###
- VaryButtonLayout的每一个子View，都是一种状态，可以添加任意个子View(状态) 

        <com.ethanco.varybuttondemo.VaryButtonLayout
            android:id="@+id/varyButton1"
            android:layout_width="100dp"
            android:layout_height="100dp">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:src="@mipmap/img1_p" />

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:src="@mipmap/img1_30" />

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:src="@mipmap/img1_45" />

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:src="@mipmap/img1_60" />
        </com.ethanco.varybuttondemo.VaryButtonLayout>

### 使用Java代码添加状态 ###
除了使用XML进行添加状态，也可以通过Java代码进行添加  

添加Layout布局

    varyButton1.addStatusView(R.layout.button_status);  
也可直接添加View

    varyButton1.addStatusView(new ImageView(this));
### 设置点击监听 ###
 	
    varyButton1.setOnVarayClickListener(new VaryButtonLayout.OnVaryClickListener() {
	    @Override
	    public void onClick(View v, int currIndex, int nextIndex) {
	        Toast.makeText(getApplicationContext(), "currIndex:" + currIndex + " nextIndex" + nextIndex, Toast.LENGTH_SHORT).show();
	    }
	});

### 设置现在的状态 ###
    varyButton1.setCurrSatus(2); //设置当前所处状态

### 子View的形态可以是任意的 ###
VaryButtonLayout的每一个子View，都是一种状态，而子view可以是单独的控件，也可以是一个布局，所以，子View的布局是可以完全自定义的

#####左边是Image，右边是TextView的情况

	<com.ethanco.varybuttondemo.VaryButtonLayout
            android:id="@+id/varyButton2"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:paddingLeft="50dp"
            android:paddingRight="50dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="horizontal">

                <ImageView
                    android:layout_width="0dp"
                    android:layout_height="match_parent"
                    android:layout_weight="1"
                    android:src="@mipmap/img1_p" />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:text="TEXT0"
                    android:textSize="22sp" />
            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="horizontal">

                <ImageView
                    android:layout_width="0dp"
                    android:layout_height="match_parent"
                    android:layout_weight="1"
                    android:src="@mipmap/img1_30" />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:text="TEXT1"
                    android:textColor="@color/colorPrimaryDark"
                    android:textSize="22sp" />
            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="horizontal">

                <ImageView
                    android:layout_width="0dp"
                    android:layout_height="match_parent"
                    android:layout_weight="1"
                    android:src="@mipmap/img1_45" />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:text="TEXT2"
                    android:textColor="@color/colorPrimary"
                    android:textSize="22sp" />
            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="horizontal">

                <ImageView
                    android:layout_width="0dp"
                    android:layout_height="match_parent"
                    android:layout_weight="1"
                    android:src="@mipmap/img1_60" />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_gravity="center"
                    android:text="TEXT3"
                    android:textColor="@color/colorAccent"
                    android:textSize="22sp" />
            </LinearLayout>
        </com.ethanco.varybuttondemo.VaryButtonLayout>

#####上边是View，下边是ImageView的情况

	<com.ethanco.varybuttondemo.VaryButtonLayout
        android:layout_width="100dp"
        android:layout_height="120dp"
        android:padding="5dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:src="@mipmap/img1_p" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="TEXT0"
                android:textSize="22sp" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:src="@mipmap/img1_30" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="TEXT1"
                android:textSize="22sp" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:src="@mipmap/img1_45" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="TEXT2"
                android:textSize="22sp" />
        </LinearLayout>

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <ImageView
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:src="@mipmap/img1_60" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="TEXT3"
                android:textSize="22sp" />
        </LinearLayout>
    </com.ethanco.varybuttondemo.VaryButtonLayout>

### 使用include ###
- 若VaryButtonLayout中只有一个子布局(为了方便，称作AView)，并且AView是一个ViewGroup，那么，就会去AView中再去寻找子View，以AView中的子View来添加状态
	- 所以，可以使用include，来简化单个XML的代码，并且使子View可复用

新建include_statuslayout.xml

	<?xml version="1.0" encoding="utf-8"?>
	<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent">
	
	    <ImageView
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:src="@mipmap/img1_p" />
	
	    <ImageView
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:src="@mipmap/img1_30" />
	
	    <ImageView
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:src="@mipmap/img1_45" />
	
	    <ImageView
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:src="@mipmap/img1_60" />
	</FrameLayout>

在Activity的xml中

    <com.ethanco.varybuttondemo.VaryButtonLayout
        android:layout_width="100dp"
        android:layout_height="100dp">

        <include layout="@layout/include_statuslayout"></include>
    </com.ethanco.varybuttondemo.VaryButtonLayout>

然后，还可以重用include_statuslayout.xml  
比如，Activity中需要有两个相同状态的VaryButtonLayout

	 <com.ethanco.varybuttondemo.VaryButtonLayout
        android:layout_width="100dp"
        android:layout_height="100dp">

        <include layout="@layout/include_statuslayout"></include>
    </com.ethanco.varybuttondemo.VaryButtonLayout>

	<com.ethanco.varybuttondemo.VaryButtonLayout
        android:layout_width="match_parent"
        android:layout_height="100dp">

        <include layout="@layout/include_statuslayout"></include>
    </com.ethanco.varybuttondemo.VaryButtonLayout>
	
