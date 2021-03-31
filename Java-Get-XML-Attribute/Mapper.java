import java.util.*;
public class Mapper
{
    Map<String, Integer> map = new HashMap<>();
    public Mapper(){
        map.put("正確:正確", 1);
        map.put("變數:重複宣告變數", 2);
        map.put("變數:變數未定義無法使用", 3);
        map.put("變數:不了解局部變數的概念", 4);
        map.put("運算:運算錯誤:型態不符無法運算", 5);
        map.put("函數:未傳遞參數或傳回值", 6);
        map.put("函數:函數庫使用錯誤", 7);
        map.put("函數:函數結構不清楚", 8);
        map.put("函數:函數概念不清楚", 9);
        map.put("字串:字串比較錯誤(沒有使用equals函數)", 10);
        map.put("io:不會使用輸入scanner類別", 11);
        map.put("io:重複宣告scanner物件", 12);
        map.put("物件:物件動作概念(建構子)", 13);
        map.put("物件:物件資料/物件動作概念", 14);
        map.put("物件:正確的產生物件", 15);
        map.put("物件:無法分辨物件&類別的資料/動作", 16);
        map.put("物件:建立物件之間的關係", 17);
        map.put("物件:不會使用物件之間的關係", 18);
        map.put("物件:不會使用物件資料", 19);
        map.put("陣列:不會宣告陣列變數", 20);
        map.put("陣列:陣列索引值", 21);
        map.put("陣列:陣列溢位", 22);
        map.put("其他:撰寫main函式", 23);
        map.put("其他:關鍵字錯誤", 24);
        map.put("其他:筆誤", 25);
        map.put("其他:不熟悉JAVA程式結構", 26);
        map.put("繼承:使用繼承", 27);
        map.put("繼承:使用super產生繼承物件", 28);
        map.put("抽象:繼承資料或動作", 29);
        map.put("抽象:抽象動作", 30);
        map.put("抽象:抽象類別不能直接產生物件", 31);
        map.put("抽象:覆寫抽象動作", 32);
        map.put("抽象:不會分離變跟不變的部分", 33);
        map.put("介面:Comparable介面", 34);
        map.put("介面:Comparable介面的排序應用", 35);
        map.put("迴圈:迴圈概念錯誤", 36);     
    }
    public Integer getIndex(String key){
        //System.out.println(key);
        return map.get(key);
    }
}
