package vn.viviu.fxpfitnesshulahoop.ui.nutrition;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Nutrition;


public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.ViewHolder> {
    private List<Nutrition> nutritions;
    private Context context;
    private AdapterNutritionCallBack callBack;
    String image;


    public NutritionAdapter(Context context, List<Nutrition> nutritions, AdapterNutritionCallBack callBack) {
        this.context = context;
        this.nutritions = nutritions;
        this.callBack = callBack;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nutrition, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Nutrition nutrition = nutritions.get(position);
        holder.tvTitle.setText(nutrition.getCategory());
        holder.tvGuide.setText(nutrition.getDescription());
        switch (nutrition.getCategory().trim()) {
            case "Bữa sáng":
                image ="Breakfast";
                break;
            case "Bữa phụ":
                image ="Snack";
                break;
            case "Bữa trưa":
                image ="Lunch";
                break;
            case "Bữa tối":
                image ="Dinner";
                break;
            default:
                image ="Breakfast";
                break;
        }
        int imgId = context.getResources().getIdentifier(image.toLowerCase(),
                "drawable", context.getPackageName());
        holder.imgNutrition.setImageResource(imgId);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (callBack != null) {
                    callBack.onItemClick(nutrition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nutritions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvGuide;
        private CircleImageView imgNutrition;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.textview_nutrition_title);
            tvGuide = itemView.findViewById(R.id.textview_nutrition_guide);
            imgNutrition = itemView.findViewById(R.id.image_nutrition);
            linearLayout = itemView.findViewById(R.id.lnNutrition);
        }
    }
}
