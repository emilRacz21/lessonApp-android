package com.example.bar;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.bar.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {
    FragmentEditProfileBinding fragmentEditProfileBinding;
    LanguageVocabulary languageVocabulary = new LanguageVocabulary();
    int choosedLang;
    public EditProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentEditProfileBinding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = fragmentEditProfileBinding.getRoot();
        choosedLang = ((MainActivity)getActivity()).value;
        DatabaseActivity databaseActivity = new DatabaseActivity(getContext());
        ((MainActivity)getActivity()).setLanguage(choosedLang, languageVocabulary);
        setHintText();
        fragmentEditProfileBinding.buttonCancel.setOnClickListener(view1-> deleteDB(databaseActivity));
        fragmentEditProfileBinding.buttonAccept.setOnClickListener(view1 -> addDB(databaseActivity));
        fragmentEditProfileBinding.backArrow.setOnClickListener(view1 -> ((MainActivity)getActivity()).setFragment(new MoreFragment()));
        updateList(databaseActivity);
        return view;
    }
    void setHintText(){
        ((MainActivity)getActivity()).setActionBar(languageVocabulary.actionBarTitle[3], R.drawable.baseline_person_24);
        fragmentEditProfileBinding.informationText.setText(languageVocabulary.editProfileLang[0]);
        fragmentEditProfileBinding.editTextText.setHint(languageVocabulary.editProfileLang[1]);
        fragmentEditProfileBinding.editTextText1.setHint(languageVocabulary.editProfileLang[2]);
        fragmentEditProfileBinding.editTextText2.setHint(languageVocabulary.editProfileLang[3]);
        fragmentEditProfileBinding.editTextText3.setHint(languageVocabulary.editProfileLang[4]);
        fragmentEditProfileBinding.editTextText4.setHint(languageVocabulary.editProfileLang[5]);
        fragmentEditProfileBinding.editTextText5.setHint(languageVocabulary.editProfileLang[6]);
        fragmentEditProfileBinding.editTextText6.setHint(languageVocabulary.editProfileLang[7]);
        fragmentEditProfileBinding.buttonAccept.setText(languageVocabulary.editProfileLang[8]);
        fragmentEditProfileBinding.buttonCancel.setText(languageVocabulary.editProfileLang[9]);
    }

    void addDB(DatabaseActivity databaseActivity){
        if(!(fragmentEditProfileBinding.editTextText5.getText().toString().isEmpty() || fragmentEditProfileBinding.editTextText6.getText().toString().isEmpty())){
            if(fragmentEditProfileBinding.editTextText5.getText().toString().equals(fragmentEditProfileBinding.editTextText6.getText().toString())){
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
                    databaseActivity.addBook(
                            fragmentEditProfileBinding.editTextText.getText().toString(),
                            fragmentEditProfileBinding.editTextText1.getText().toString(),
                            fragmentEditProfileBinding.editTextText2.getText().toString(),
                            fragmentEditProfileBinding.editTextText3.getText().toString(),
                            fragmentEditProfileBinding.editTextText4.getText().toString(),
                            fragmentEditProfileBinding.editTextText5.getText().toString(),
                            fragmentEditProfileBinding.editTextText6.getText().toString()
                    );
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
        fragmentEditProfileBinding.dbResult.setText(builder.toString());
    }
}
