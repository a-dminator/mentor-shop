package io.adev.mentor_shop.view.java;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.adev.mentor_shop.DIKt;
import io.adev.mentor_shop.R;
import io.adev.mentor_shop.entities.Category;
import io.adev.mentor_shop.presentation.CategoriesPresenter;
import io.adev.mentor_shop.presentation.CategoriesView;
import io.adev.mentor_shop.view.CategoriesAdapter;

public class JavaCategoriesActivity extends AppCompatActivity implements CategoriesView {

    private CategoriesPresenter presenter = DIKt.getCategoriesPresenter();

    private CategoriesAdapter categoriesAdapter;

    private ProgressBar loaderView;
    private LinearLayout contentView;
    private RecyclerView categoriesView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        categoriesAdapter = new CategoriesAdapter(this);

        loaderView = findViewById(R.id.loaderView);
        contentView = findViewById(R.id.contentView);

        categoriesView = findViewById(R.id.categoriesView);
        categoriesView.setLayoutManager(new LinearLayoutManager(this));
        categoriesView.setAdapter(categoriesAdapter);

        presenter.onCreateView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroyView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAppear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onDisappear();
    }

    @Override
    public void displayLoading() {
        loaderView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    @Override
    public void displayCategories(@NotNull List<Category> categories) {
        loaderView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
        categoriesAdapter.update(categories);
    }

}
