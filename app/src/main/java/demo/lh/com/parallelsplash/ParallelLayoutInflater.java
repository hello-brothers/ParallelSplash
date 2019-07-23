package demo.lh.com.parallelsplash;

import android.content.Context;
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

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new ParallelLayoutInflater(this, newContext, fragment);
    }

    class ParallelFactory implements Factory2{

        private LayoutInflater inflater;

        public ParallelFactory(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        /**
         * 监听回调
         * @param parent 跟view
         * @param name 每次回调过来的控件名字
         * @param context 上下文
         * @param attrs 布局里面的全部属性集合
         * @return
         */
        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            Log.i(TAG, "onCreateView: " + name);
            return null;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return null;
        }
    }
}
