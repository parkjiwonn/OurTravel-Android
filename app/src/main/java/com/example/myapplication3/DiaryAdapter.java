package com.example.myapplication3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>{


    private  ArrayList<DiaryData> diaryData = null;

    public DiaryAdapter(ArrayList<DiaryData> Ddata){
        diaryData = Ddata;
    }

    interface OnItemClickListener{
        void onItemClick(View v, int dpos);
        void onEditClick(View v, int dpos); //수정
        void onDeleteClick(View v, int dpos);//삭제
    }
    // 커스텀 리스너 인터페이스 정의

    private DiaryAdapter.OnItemClickListener dListener = null;
    //전달된 객체를 저장할 변수 dListener 추가함.

    public void setOnItemClickListener(OnItemClickListener listener){
        this.dListener = listener;
    }
    //리스너 객체를 전달하는 매서드





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context dcontext = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) dcontext.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        // layoutinflater는 xml에 미리 정의해둔 툴을 실제 메모리에 올려준다. inflater는 부풀리다라는 의미


        View view = inflater.inflate (R.layout.diaryitem, parent, false);
        // Xml에 덩의된 resource를 view 객체로 반환해주는 역할을 한다.
        // 레이아웃정의부에서 실행되고 메모리 로딩이 돼 화면에 띄워주는 역할.

        //false는 바로 인플레이션 하지 x, true는 바로 인플에이션 한다.
        DiaryAdapter.ViewHolder dvh = new DiaryAdapter.ViewHolder(view);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DiaryData diary = diaryData.get (position);
        //holder.tv_name.setText (data.getName ());
        holder.tx_triptitle.setText(diary.getTitle());
        holder.tx_where.setText(diary.getPlace());
        holder.tx_start.setText(diary.getStart());
        holder.tx_finish.setText(diary.getFinish());
    }

    @Override
    public int getItemCount() {
        return diaryData.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tx_triptitle, tx_where, tx_start, tx_finish;
        Button edit_diary, delete_diary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tx_triptitle = itemView.findViewById (R.id.tx_triptitle);
            this.tx_where = itemView.findViewById (R.id.tx_where);
            this.tx_start = itemView.findViewById (R.id.tx_start);
            this.tx_finish = itemView.findViewById (R.id.tx_finish);
            this.edit_diary = itemView.findViewById(R.id.edit_diary);
            this.delete_diary = itemView.findViewById(R.id.delete_diary);

            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    int dpos = getAdapterPosition ();
                    if (dpos!=RecyclerView.NO_POSITION){
                        //리스너 객체의 호출
                        if (dListener!=null){
                            dListener.onItemClick (view,dpos);

                        }
                    }
                }
            });
            // 아이템 클릭 이벤트 핸들러 메서드에서 리스너 객체 (onitemclick) 매서드 호출

            edit_diary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int dpos = getAdapterPosition();
                    if(dpos != RecyclerView.NO_POSITION){
                        if(dListener != null){
                            dListener.onEditClick(view, dpos);
                        }
                    }
                }
            });

            delete_diary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int dpos = getAdapterPosition();
                    if(dpos != RecyclerView.NO_POSITION){
                        if(dListener != null){
                            dListener.onDeleteClick(view, dpos);
                        }
                    }
                }
            });




        }
    }
}
