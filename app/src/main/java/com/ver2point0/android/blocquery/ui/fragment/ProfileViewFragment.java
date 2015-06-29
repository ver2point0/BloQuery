package com.ver2point0.android.blocquery.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.ver2point0.android.blocquery.ui.activity.BloQueryActivity;
import com.ver2point0.android.blocquery.view.SquareImageView;
import com.ver2point0.android.bloquery.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProfileViewFragment extends Fragment {

    private ParseUser mParseUser;
    private SquareImageView mProfilePicture;

    public ProfileViewFragment() {}

    public void setParseUser(ParseUser user) {
        mParseUser = user;
    }

    private void setProfilePicture(Bitmap bitmapPicture) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        final ParseFile parseFilePicture = new ParseFile(mParseUser.getObjectId() + ".png", byteArrayOutputStream.toByteArray());
        parseFilePicture.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // put user picture
                    mParseUser.put("profile", parseFilePicture);
                    mParseUser.saveInBackground();
                } else {
                    e.printStackTrace();
                }
            }
        });
        // updated image
        mProfilePicture.setImageBitmap(bitmapPicture);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile_view, viewGroup, false);

        TextView name = (TextView) view.findViewById(R.id.tv_name);
        final TextView description = (TextView) view.findViewById(R.id.tv_description);
        mProfilePicture = (SquareImageView) view.findViewById(R.id.iv_profile_image);
        ImageButton menuButton = (ImageButton) view.findViewById(R.id.ib_edit_menu);
        final EditText editDescription = (EditText) view.findViewById(R.id.et_description);
        Button saveButton = (Button) view.findViewById(R.id.bt_save);
        Button cancelButton = (Button) view.findViewById(R.id.bt_cancel);
        final LinearLayout linearLayoutButtons = (LinearLayout) view.findViewById(R.id.ll_edit_buttons);

        // set values for user
        if (mParseUser != null) {

            // set name
            name.setText(mParseUser.getString("first_name") + " " + mParseUser.getString("last_name"));

            // set description
            if (mParseUser.getString("description") != null) {
                description.setText(mParseUser.getString("description"));
                editDescription.setText(mParseUser.getString("description"));
            }

            // get profile picture
            if (mParseUser.get("profile") != null) {

                ParseFile profileImageFile = mParseUser.getParseFile("profile");
                profileImageFile.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, ParseException e) {
                        if (e == null) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            mProfilePicture.setImageBitmap(bitmap);
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                // get a generic profile picture
                mProfilePicture.setImageResource(R.mipmap.ic_launcher);
            }

            // menu
            if (mParseUser.getUsername().equals(ParseUser.getCurrentUser().getUsername())) {

                // show menu
                menuButton.setVisibility(View.VISIBLE);

                // create pop up menu
                menuButton.setFocusable(false);
                final PopupMenu popUpMenu = new PopupMenu(getActivity(), menuButton);
                popUpMenu.getMenu().add(Menu.NONE, 0, Menu.NONE, getString(R.string.update_profile_pic));
                popUpMenu.getMenu().add(Menu.NONE, 1, Menu.NONE, getString(R.string.edit_description));

                // menu button click listener
                menuButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popUpMenu.show();
                    }
                });

                // menu item click listeners
                popUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case 0:
                                // start intent for new picture
                                Intent intent = new Intent(Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, 555);
                                break;
                            case 1:
                                // edit description
                                description.setVisibility(View.GONE);
                                editDescription.setVisibility(View.VISIBLE);
                                linearLayoutButtons.setVisibility(View.VISIBLE);
                                break;
                        }
                        return false;
                    }
                });
            } else {
                menuButton.setVisibility(View.INVISIBLE);
            }

            // edit description button listeners
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // show text view, hide edit
                    description.setVisibility(View.VISIBLE);
                    editDescription.setVisibility(View.GONE);
                    linearLayoutButtons.setVisibility(View.GONE);
                }
            });

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // save description
                    mParseUser.put("description", editDescription.getText().toString());
                    mParseUser.saveInBackground();

                    // update text view
                    description.setText(editDescription.getText().toString());

                    // show text view, hide edit
                    description.setVisibility(View.VISIBLE);
                    editDescription.setVisibility(View.GONE);
                    linearLayoutButtons.setVisibility(View.GONE);
                }
            });
        }
        return view;
    } // end onCreateView() method

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 555 && resultCode == BloQueryActivity.RESULT_OK && data != null) {
            // get image
            Uri imageUri = data.getData();
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                Bitmap.createScaledBitmap(image, 500, 500, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setProfilePicture(image);
        }
    }















} // end class ProfileViewFragment
