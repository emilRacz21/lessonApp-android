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
    LanguageVocabulary languageVocabulary = new LanguageVocabulary();
    int choosedLang;
    EditText[] editTexts;
    public EditProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        profileBinding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = profileBinding.getRoot();
        choosedLang = ((MainActivity)getActivity()).value;
        DatabaseActivity databaseActivity = new DatabaseActivity(getContext());
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        setHintText();
        profileBinding.buttonCancel.setOnClickListener(view1-> deleteDB(databaseActivity));
        profileBinding.buttonAccept.setOnClickListener(view1 -> addDB(databaseActivity));
        profileBinding.backArrow.setOnClickListener(view1 -> ((MainActivity)getActivity()).setFragment(new MoreFragment()));
        updateList(databaseActivity);
        return view;
    }
    void setHintText(){
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[3], R.drawable.baseline_person_24);
        profileBinding.informationText.setText(languageVocabulary.editProfileLang[0]);
        String[] hints = {
                languageVocabulary.editProfileLang[1],
                languageVocabulary.editProfileLang[2],
                languageVocabulary.editProfileLang[3],
                languageVocabulary.editProfileLang[4],
                languageVocabulary.editProfileLang[5],
                languageVocabulary.editProfileLang[6],
                languageVocabulary.editProfileLang[7]
        };
        editTexts= new EditText[]{
                profileBinding.editTextText,
                profileBinding.editTextText1,
                profileBinding.editTextText2,
                profileBinding.editTextText3,
                profileBinding.editTextText4,
                profileBinding.editTextText5,
                profileBinding.editTextText6,
        };
        for (int i = 0; i < editTexts.length ; i++){
            editTexts[i].setHint(hints[i]);
        }
        profileBinding.buttonAccept.setText(languageVocabulary.editProfileLang[8]);
        profileBinding.buttonCancel.setText(languageVocabulary.editProfileLang[9]);
    }

    void addDB(DatabaseActivity databaseActivity){
        if(!(profileBinding.editTextText5.getText().toString().isEmpty() || profileBinding.editTextText6.getText().toString().isEmpty())){
            if(profileBinding.editTextText5.getText().toString().equals(profileBinding.editTextText6.getText().toString())){
                makeDialog(databaseActivity);
            }else{
                Toast.makeText(getContext(), languageVocabulary.toastMessage[1],Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getContext(), languageVocabulary.toastMessage[0],Toast.LENGTH_SHORT).show();
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
                .setTitle(languageVocabulary.dialog[0])
                .setMessage(languageVocabulary.dialog[1])
                .setCancelable(false)
                .setIcon(R.drawable.baseline_warning_24)
                .setPositiveButton(languageVocabulary.dialog[2],(dialog, which) -> {
                    databaseActivity.addBookArray(editTexts);
                    updateList(databaseActivity);
                    dialog.dismiss();
                })
                .setNegativeButton(languageVocabulary.dialog[3], (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void updateList(DatabaseActivity db) {
        Cursor c = db.getAllBook();
        StringBuilder builder = new StringBuilder();
        while (c.moveToNext()) {
            builder.append("ID:" + c.getInt(0));
            for(int i = 0, n = 1; i< languageVocabulary.database.length; i++, n++){
                builder.append("\n"+ languageVocabulary.database[i] + ": "+ c.getString(n));
            }
            builder.append("\n---------------------------\n");
        }
        profileBinding.dbResult.setText(builder.toString());
    }
}
