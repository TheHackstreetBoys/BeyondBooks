package in.ac.iiitv.beyondbooks;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewBook.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewBook#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewBook extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    float rating;
    String title;
    Long imagelink;
    TextView tit;
    RatingBar rat;
    ImageView imageView;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewBook.
     */
    // TODO: Rename and change types and number of parameters
    public static NewBook newInstance(NewlyAdded naa) {
        NewBook fragment = new NewBook(naa);

        return fragment;
    }


    public NewBook(NewlyAdded newlyAdded) {
        imagelink = newlyAdded.getId();
        title = newlyAdded.getBook_name();
        rating = newlyAdded.getRatings();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_book, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rat = (RatingBar) getView().findViewById(R.id.frag_new_book_rating);
        tit = (TextView) getView().findViewById(R.id.frag_new_book_title);
        tit.setText(title);
        imageView = (ImageView)getView().findViewById(R.id.frag_new_book_image);
        RequestServer requestServer = new RequestServer();
        System.out.println("image isbn is " +imagelink.toString()+"adsgafafsa");
        imageView.setImageBitmap(requestServer.getImage(imagelink.toString()+".jpg"));
        rat.setRating(rating);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
