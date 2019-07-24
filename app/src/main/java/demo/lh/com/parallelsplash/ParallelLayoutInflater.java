package demo.lh.com.parallelsplash;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 填充器 解析xml
 */
public class ParallelLayoutInflater extends LayoutInflater {

    private ParallelFragment fragment;
    private static final String TAG = ParallelContainer.class.getSimpleName();

    protected ParallelLayoutInflater(LayoutInflater original, Context newContext, ParallelFragment fragment) {
        super(original, newContext);
        this.fragment = fragment;
        setFactory2(new ParallelFactory(this));
    }
    protected ParallelLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    /**
     * 系统调用
     * @param newContext
     * @return
     */
    @Override
    public LayoutInflater cloneInContext(Context newContext) {
//        return new ParallelLayoutInflater(this, newContext, fragment);
        return null;
    }

    class ParallelFactory implements Factory2{

        private LayoutInflater inflater;
        private String[] sClassPrefix = {
                "android.widget.",
                "android.view."
        };

        public ParallelFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        /**
         * 监听回调 每解析一个控件就回调一次
         * @param parent 根view 顶级view
         * @param name 每次回调过来的控件名字
         * @param context 上下文
         * @param attrs 布局里面的全部属性集合
         * @return
         */
        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            Log.i(TAG, "onCreateView1: " + name);
            View view = null;
            view = createMyView(name, context, attrs);
            if (view!=null){
                TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.splash_attr);
                if (typedArray!=null && typedArray.length()>0){
                    ParallelViewTag pv = new ParallelViewTag();
                    pv.alphaIn = typedArray.getFloat(R.styleable.splash_attr_a_in, 0);
                    pv.alphaOut = typedArray.getFloat(R.styleable.splash_attr_a_out, 0);
                    pv.xIn = typedArray.getFloat(R.styleable.splash_attr_x_in, 0);
                    pv.xOut = typedArray.getFloat(R.styleable.splash_attr_x_out, 0);
                    pv.yIn = typedArray.getFloat(R.styleable.splash_attr_y_in, 0);
                    pv.yOut = typedArray.getFloat(R.styleable.splash_attr_y_out, 0);
                    view.setTag(R.id.parallax_view_tag, pv);
                }
                fragment.getParallelView().add(view);
                typedArray.recycle();
            }
            return view;
        }

        private View createMyView(String name, Context context, AttributeSet attrs) {
            /**
             * 判断是否是自定义控件
             *  系统控件一般有三种前缀
             *      android.widget
             *      android.view
             *      android.webkit 不常用
             *
             */
            if (name.contains(".")){
                return reflectView(name, null, context, attrs);
            }else {
                for (int i = 0; i < sClassPrefix.length; i++) {
                    View view = reflectView(name, sClassPrefix[i], context, attrs);
                    if (view!=null){
                        return view;
                    }
                }
            }
            return null;
        }

        private View reflectView(String name, String prefix, Context context, AttributeSet attrs) {
            try {
                //通过系统的inflater来创建视图，读取系统属性
                return inflater.createView(name, prefix, attrs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;

            }
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            Log.i(TAG, "onCreateView2: " + name);
            return null;
        }
    }


}
