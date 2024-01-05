package com.example.bar;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.example.bar.databinding.FragmentEditProfileBinding;
public class EditProfileFragment extends Fragment {
    FragmentEditProfileBinding profileBinding;
    String[] database;
    EditText[] editTexts;
    public EditProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        database = getResources().getStringArray(R.array.database);
        profileBinding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = profileBinding.getRoot();
        DatabaseActivity databaseActivity = new DatabaseActivity(getContext());
        editTexts= new EditText[]{
                profileBinding.editTextText,
                profileBinding.editTextText1,
                profileBinding.editTextText2,
                profileBinding.editTextText3,
                profileBinding.editTextText4,
                profileBinding.editTextText5,
                profileBinding.editTextText6,
        };
        profileBinding.buttonCancel.setOnClickListener(view1-> deleteDB(databaseActivity));
        profileBinding.buttonAccept.setOnClickListener(view1 -> addDB(databaseActivity));
        profileBinding.backArrow.setOnClickListener(view1 -> ((MainActivity)getActivity()).setFragment(new MoreFragment()));
        updateList(databaseActivity);
        return view;
    }
    void addDB(DatabaseActivity databaseActivity){
        if(!(profileBinding.editTextText5.getText().toString().isEmpty() || profileBinding.editTextText6.getText().toString().isEmpty())){
            if(profileBinding.editTextText5.getText().toString().equals(profileBinding.editTextText6.getText().toString())){
                makeDialog(databaseActivity);
            }else{
                Toast.makeText(getContext(), getResources().getString(R.string.podaj_takie_same_hasła),Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getContext(), getResources().getString(R.string.uzupenij_wszytskie_pola),Toast.LENGTH_SHORT).show();
        }
    }
    void deleteDB(DatabaseActivity databaseActivity){
        Cursor cursor = databaseActivity.getAllBook();
        if(cursor.moveToLast()){
            int id = cursor.getInt(0);
            databaseActivity.deleteAllBooks(String.valueOf(id));
            updateList(databaseActivity);
        }
    }
    void makeDialog(DatabaseActivity databaseActivity){
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(getResources().getString(R.string.uwaga))
                .setMessage(getResources().getString(R.string.czy_jestes_pewny))
                .setCancelable(false)
                .setIcon(R.drawable.baseline_warning_24)
                .setPositiveButton(getResources().getString(R.string.tak),(dialog, which) -> {
                    databaseActivity.addBookArray(editTexts);
                    updateList(databaseActivity);
                    dialog.dismiss();
                })
                .setNegativeButton(getResources().getString(R.string.nie), (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void updateList(DatabaseActivity db) {
        Cursor c = db.getAllBook();
        StringBuilder builder = new StringBuilder();
        while (c.moveToNext()) {
            builder.append("ID:" + c.getInt(0));
            for(int i = 0, n = 1; i< database.length; i++, n++){
                builder.append("\n"+ database[i] + ": "+ c.getString(n));
            }
            builder.append("\n---------------------------\n");
        }
        profileBinding.dbResult.setText(builder.toString());
    }
}