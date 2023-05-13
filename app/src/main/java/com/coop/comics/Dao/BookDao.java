package com.coop.comics.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.coop.comics.Model.Book;
import com.coop.comics.Model.ComicData;
import com.coop.comics.R;

import java.util.ArrayList;

public class BookDao extends SQLiteOpenHelper {

    public BookDao(Context context) {
        super(context, "comics.db", null, 1);
    }
    ArrayList<ComicData> pages = new ArrayList<ComicData>();
    public void initBook(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book(1,"战火中的青春",R.mipmap.a01_index));
        bookList.add(new Book(2,"上甘岭",R.mipmap.a02_index));
        bookList.add(new Book(3,"百合花",R.mipmap.a0301));
        bookList.add(new Book(4,"平民的故事",R.mipmap.a0401));

        initBook_one();  //初始化第一本书
        initBook_two();  //初始化第二本书

        for (int i = 0; i < bookList.size(); i++) {
            ContentValues book_newValues = new ContentValues();  //存放书籍内容
            book_newValues.put("book_name",bookList.get(i).getName());
            book_newValues.put("book_photo",bookList.get(i).getPhoto());
            db.insert("book",null,book_newValues);
        }

        for (int i = 0; i < pages.size(); i++) {
            ContentValues pageValues = new ContentValues();   //存放页面内容
            pageValues.put("imageResId",pages.get(i).getImageResId());
            pageValues.put("bookId",pages.get(i).getBookId());
            pageValues.put("title",pages.get(i).getTitle());
            pageValues.put("summary",pages.get(i).getSummary());
            pageValues.put("page",pages.get(i).getPage());
            pageValues.put("isCollection",pages.get(i).isCollection());
            pageValues.put("isBookmark",pages.get(i).isBookmark());
            db.insert("pages",null,pageValues);
        }
    }
    public ArrayList<Book> queryBooks(){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Book> books = new ArrayList<Book>();
        //查询所有book
        Cursor results = db.query("book",null,null,null,null,null,null);
        int resultCounts = results.getCount();

        if(resultCounts==0||!results.moveToFirst()){
            return books;
        }

        for(int i=0;i<resultCounts;i++){
            @SuppressLint("Range") Book book = new Book(i+1,results.getString(results.getColumnIndex("book_name")),results.getInt(results.getColumnIndex("book_photo")) );
            books.add(book);
            results.moveToNext();
        }
        return books;
    }

    public void initBook_one(){
        pages.add(new ComicData(R.mipmap.a01_cover,1,"战火中的青春","陆柱国   原著\n卢陵   改编\n曲羊   绘画",-1));
        pages.add(new ComicData(R.mipmap.a01_breif,1,"简介页",
                "故事发生于苏北。1946年冬天,我解放重某部,解救了被敌人圉困在山头上的区小队长高山,并把他分配到作战勇猛、驩名远近的尖刀英雄排为副排长。\n" +
                        "这位矮小瘦弱、穿着破烂的副排长最初并不受人欢迎,排长雷振林认为这是舱英雄排丢人,挑皮的战士也认为他尺4不够。但是高山严懂地执行了自己的职责,在工作和战斗中显示了他的机智与才能,逐渐赢得全排的信任。一次奇袭敌人营部时,排长雷振林由于个人英雄主义,不听劝貌,光图一时勇气追击敌人,只身困入重圉被高山救出。就在这次战斗中高山负重伤入院了。\n" +
                        "·当雷振林到医院去探望高山时,才知道这位机智、善战的副排长是位女同志……。此后他價虽然分手了，但是共同战斗的日子,使他胚结成了难忘的友谊。\n"
                ,0));
        pages.add(new ComicData(R.mipmap.a0101,1,"第一幕",
                "1946年冬天，国民党反动派向老根据地进攻，某县区小队被敌人包图在山头上。群多同志都相朱牺性了,就剩下一个名叫高山的区小队长，对付着山下的敌人。"
                ,1));
        pages.add(new ComicData(R.mipmap.a0102,1,"第二幕",
                "大批敌人嗥叫着冲上来，高山愤怒地跳到岩石上，操起机榆向敌人射击。"
                ,2));
        pages.add(new ComicData(R.mipmap.a0103,1,"第三幕",
                "机枪没子弹了,背后山脚下又喊起了杀声,敌人包围上来了。"
                ,3));
        pages.add(new ComicData(R.mipmap.a0104,1,"第四幕",
                "高山猛然跳起把机枪砸碎,拾起一支步枪继续向敌人射击。"
                ,4));
        pages.add(new ComicData(R.mipmap.a0105,1,"第五幕",
                "步枪的弹槽也室了,他拿起最后一棵手榴弹,迎着敌人的喊柔声走去,准备与敌人同归于尽。"
                ,5));
        pages.add(new ComicData(R.mipmap.a0106,1,"第六幕",
                "突然一声巨响，炮僤在敌人背后开了花,敌人退下去了，远处我解放军野战部队赶到了，雄体的审号声响彻山野。"
                ,6));
        pages.add(new ComicData(R.mipmap.a0107,1,"第七幕",
                "解放军野战部队的一个身材高大的团长快马加鞭赶过步兵战士。"
                ,7));
        pages.add(new ComicData(R.mipmap.a0108,1,"第八幕",
                "狭窄的山谷里飞奔出尖刀英雄排排长雷振林,他后面是高个子的一班长“仙鹤”和小胖等战士们。"
                ,8));
        pages.add(new ComicData(R.mipmap.a0109,1,"第九幕",
                "由于英雄排长等人的出现、高山得救了，他长吁一口气,软瘫地倚在巨石上,步枪从他的手中掉落。"
                ,9));
        pages.add(new ComicData(R.mipmap.a0110,1,"第十幕",
                "这时团长看到不注意隐蔽又椅个指押刀的雷振林，勒合他把刀交上来,雷振林却再三睛求准许他使用这把刚徼来的指押刀。"
                ,10));
    }
    public void initBook_two(){
        pages.add(new ComicData(R.mipmap.a02_cover,2,"封面页",
                "封面内容"
                ,-1));
        pages.add(new ComicData(R.mipmap.a02_breif,2,"简介页",
                "简介内容"
                ,0));
        pages.add(new ComicData(R.mipmap.a0201,2,"第一幕",
                "1952年秋天的清晨,驻守上甘岭主睾陕地的一速速长張文贵接到通知,说师长即来这里视察。他走出坑道，到山士幻那边去迎接师长。"
                ,1));
        pages.add(new ComicData(R.mipmap.a0202,2,"第二幕",
                "他沿着交通沟往前走了一程,就在一个拐弯处,碰上了师长崔信僳。"
                ,2));
        pages.add(new ComicData(R.mipmap.a0203,2,"第三幕",
                "張文贵陪着师长视察了坑道,然后来到观察所。师长向前沿障地看了几分钟光景,就地坐在一块石头上,周道:“張文贵，你知道我今天来这里的用意么?”"
                ,3));
        pages.add(new ComicData(R.mipmap.a0204,2,"第四幕",
                "(4）师长接着又说，最近以来，敌人整师整师的往这一带钢动兵力，范佛里特、李承晚亲自来看地形，他们准备了几个月，看情形就要向这里进攻。"
                ,4));
    }
    public void initBook_three(){

    }
    public void initBook_four(){

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
