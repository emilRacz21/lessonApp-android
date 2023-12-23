package com.example.bar;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bar.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentEditProfileBinding fragmentEditProfileBinding;
    private String mParam1;
    private String mParam2;
    Language language = new Language();
    int choosedLang;
    public EditProfileFragment() {}
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fragmentEditProfileBinding = FragmentEditProfileBinding.inflate(inflater, container, false);
        View view = fragmentEditProfileBinding.getRoot();
        choosedLang = ((MainActivity)getActivity()).value;
        DataBase dataBase = new DataBase(getContext());
        ((MainActivity)getActivity()).setLanguage(choosedLang, language);
        setHintText();
        fragmentEditProfileBinding.buttonCancel.setOnClickListener(view1->{
            ((MainActivity)getActivity()).setFragment(new MoreFragment());
        });
        fragmentEditProfileBinding.buttonAccept.setOnClickListener(view1 -> addDB(dataBase));
        fragmentEditProfileBinding.buttonDel.setOnClickListener(view1 -> deleteDB(dataBase));
        return view;
    }
    void setHintText(){
        ((MainActivity)getActivity()).setActionBar(language.actionBarTitle[3], R.drawable.baseline_person_24);
        fragmentEditProfileBinding.informationText.setText(language.editProfileLang[0]);
        fragmentEditProfileBinding.editTextText.setHint(language.editProfileLang[1]);
        fragmentEditProfileBinding.editTextText1.setHint(language.editProfileLang[2]);
        fragmentEditProfileBinding.editTextText2.setHint(language.editProfileLang[3]);
        fragmentEditProfileBinding.editTextText3.setHint(language.editProfileLang[4]);
        fragmentEditProfileBinding.editTextText4.setHint(language.editProfileLang[5]);
        fragmentEditProfileBinding.editTextText5.setHint(language.editProfileLang[6]);
        fragmentEditProfileBinding.editTextText6.setHint(language.editProfileLang[7]);
        fragmentEditProfileBinding.buttonAccept.setText(language.editProfileLang[8]);
        fragmentEditProfileBinding.buttonCancel.setText(language.editProfileLang[9]);
    }

    void addDB(DataBase dataBase){
        if(!(fragmentEditProfileBinding.editTextText5.getText().toString().isEmpty() || fragmentEditProfileBinding.editTextText6.getText().toString().isEmpty())){
            if(fragmentEditProfileBinding.editTextText5.getText().toString().equals(fragmentEditProfileBinding.editTextText6.getText().toString())){
                dataBase.addBook(
                        fragmentEditProfileBinding.editTextText.getText().toString(),
                        fragmentEditProfileBinding.editTextText1.getText().toString(),
                        fragmentEditProfileBinding.editTextText2.getText().toString(),
                        fragmentEditProfileBinding.editTextText3.getText().toString(),
                        fragmentEditProfileBinding.editTextText4.getText().toString(),
                        fragmentEditProfileBinding.editTextText5.getText().toString(),
                        fragmentEditProfileBinding.editTextText6.getText().toString()
                );
                updateList(dataBase);
            }else{
                Toast.makeText(getContext(),language.toastMessage[1],Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(getContext(), language.toastMessage[0],Toast.LENGTH_SHORT).show();
        }
    }
    void deleteDB(DataBase dataBase){
        Cursor cursor = dataBase.getAllBook();
        if(cursor.moveToLast()){
            int id = cursor.getInt(0);
            dataBase.deleteAllBooks(String.valueOf(id));
            updateList(dataBase);
        }
    }
    private void updateList(DataBase db) {
        Cursor c = db.getAllBook();
        StringBuilder builder = new StringBuilder();
        while (c.moveToNext()) {
            builder.append("ID:" + c.getInt(0));
            for( int i = 0, n = 1; i<language.database.length; i++, n++){
                builder.append("\n"+language.database[i] + ": "+ c.getString(n));
            }
            builder.append("\n---------------------------\n");
        }
        fragmentEditProfileBinding.dbResult.setText(builder.toString());
    }
}
