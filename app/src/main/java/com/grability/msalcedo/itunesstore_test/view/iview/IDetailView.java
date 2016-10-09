package com.grability.msalcedo.itunesstore_test.view.iview;

/**
 * Created by Mariangela Salcedo (msalcedo047@gmail.com) on 09/10/16.
 * Copyright (c) 2016 m-salcedo. All rights reserved.
 */
public interface IDetailView extends IBaseView {

    void setIcon(String url);

    void setTitle(String title);

    void setAuthor(String author);

    void setCategory(String category);

    void setDescription(String description);

    void setType(String type);

    void setPackage(String string);

    void setDate(String date);

    void setRight(String right);

    void showBtnMore();

    void hideBtnMore();

    void setMaxLinesDescription(int maxLines);

    void setTextBtn(int label);

    void setLink(String url);
}
