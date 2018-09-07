package vn.viviu.fxpfitnesshulahoop.ui.nutrition;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.viviu.fxpfitnesshulahoop.R;
import vn.viviu.fxpfitnesshulahoop.data.database.entity.Nutrition;

/**
 * A simple {@link Fragment} subclass.
 */
public class NutritionDetailsFragment extends Fragment {
    private Nutrition nutrition;
    private TextView tvTitle, tvDescription, tvHealthyChoices;
    private ImageView imgNutrition,btnBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition_details, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tvTitle = view.findViewById(R.id.textview_nutrition_title_details);
        tvDescription = view.findViewById(R.id.textview_nutrition_des_details);
        tvHealthyChoices = view.findViewById(R.id.textview_nutrition_healthy);
        imgNutrition = view.findViewById(R.id.image_nutrition_details);
        btnBack = view.findViewById(R.id.btn_back_nutritiondetails);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Bundle bundle = getArguments();
        nutrition = (Nutrition) bundle.getSerializable("Nutrition");
        assert nutrition != null;
        tvTitle.setText(nutrition.getCategory());
        tvDescription.setText(nutrition.getDescription());
        tvHealthyChoices.setText(nutrition.getHealthyChoices());

        tvHealthyChoices.setText("- " + nutrition.getHealthyChoices().replaceAll(",", ("\n" + "- ")));
        String image;
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
        int imgId = getActivity().getResources().getIdentifier(image.toLowerCase(),
                "drawable", getActivity().getPackageName());
        imgNutrition.setImageResource(imgId);
    }
}
