package com.abuke.bigataosha;
import static com.abuke.bigataosha.Bigataosha.*;
public class LangHelper {
    public static String String_Message_SystemMsg_LatestTime = null;
    public static String String_Name_Catcher = null;
    public static String String_Name_Runner = null;
    public static String String_Message_ScoreBoard_LoadSuccseefully = null;
    public static String String_Message_Default_win = null;
    public static String String_GameName = null;
    public static String String_Message_Command_Tp = null;
    public static String String_Message_Command_ErrorValue = null;
    public static String String_Message_Default_to = null;
    public static String String_Message_Default_welcometo = null;
    public static String String_Message_Default_haveagoodtime = null;
    public static String String_Message_Default_Mr_Miss_ = null;
    public static String String_Message_Default_Successfully = null;
    public static String String_Message_ScoreBoard_BoardTitle = null;
    public static String String_Message_ScoreBoard_SendBoardSuccessfully = null;
    public static String String_Message_Default_Already = null;
    public static String String_Message_Default_YouAre = null;
    public static String String_Message_Default_ServerTPS = null;

    //Item
    public static String String_Item_SpeedPlusApple = null;
    public static String String_Item_SpeedPlusApple_Description = null;
    public static String String_Item_Booger = null;
    public static String String_Item_Booger_Description = null;
    public static String String_Item_CrossBow = null;
    public static String String_Item_CrossBow_Description = null;
    public static String String_Item_BoogerWater = null;
    public static String String_Item_BoogerWater_Description = null;
    public static String String_Item_FoundItem = null;
    public static String String_Item_FoundItem_Description = null;
    public static String String_Item_Key = null;
    public static String String_Item_Key_Description = null;
    public static String String_Item_HealthyWater = null;
    public static String String_Item_HealthyWater_Description = null;
    public static String String_Item_HomeWork = null;
    public static String String_Item_HomeWork_Description = null;
    //Item.end

    //称号
    public static String String_Called_VIP = null;
    public static String String_Called_VIPULTRA = null;
    public static String String_Called_VIPPRO = null;
    public static String String_Called_OP = null;
    public static String String_Called_PLUGINMAKER = null;
    public static void Init(){
        String_GameName = pluginMain.getConfig().getString("String_GameName");
        String_Message_SystemMsg_LatestTime = pluginMain.getConfig().getString("String_Message_SystemMsg_LatestTime");
        String_Name_Catcher = pluginMain.getConfig().getString("String_Name_Catcher");
        String_Name_Runner = pluginMain.getConfig().getString("String_Name_Runner");

        //Items
        String_Item_Booger = pluginMain.getConfig().getString("String_Item_Booger");
        String_Item_Booger_Description = pluginMain.getConfig().getString("String_Item_Booger_Description");

        String_Item_SpeedPlusApple = pluginMain.getConfig().getString("String_Item_SpeedPlusApple");
        String_Item_SpeedPlusApple_Description = pluginMain.getConfig().getString("String_Item_SpeedPlusApple_Description");

        String_Item_CrossBow = pluginMain.getConfig().getString("String_Item_CrossBow");
        String_Item_CrossBow_Description = pluginMain.getConfig().getString("String_Item_CrossBow_Description");

        String_Item_BoogerWater = pluginMain.getConfig().getString("String_Item_BoogerWater");
        String_Item_BoogerWater_Description = pluginMain.getConfig().getString("String_Item_BoogerWater_Description");

        String_Item_FoundItem = pluginMain.getConfig().getString("String_Item_FoundItem");
        String_Item_FoundItem_Description = pluginMain.getConfig().getString("String_Item_FoundItem_Description");

        String_Item_Key = pluginMain.getConfig().getString("String_Item_Key");
        String_Item_Key_Description = pluginMain.getConfig().getString("String_Item_Key_Description");

        String_Item_HealthyWater = pluginMain.getConfig().getString("String_Item_HealthyWater");
        String_Item_HealthyWater_Description = pluginMain.getConfig().getString("String_Item_HealthyWater_Description");

        String_Item_HomeWork = pluginMain.getConfig().getString("String_Item_HomeWork");
        String_Item_HomeWork_Description = pluginMain.getConfig().getString("String_Item_HomeWork_Description");
        //Items.end

        //ScoreBoards
        String_Message_ScoreBoard_LoadSuccseefully = pluginMain.getConfig().getString("String_Message_ScoreBoard_LoadSuccessfully");
        String_Message_ScoreBoard_BoardTitle = pluginMain.getConfig().getString("String_Message_ScoreBoard_BoardTitle");
        String_Message_ScoreBoard_SendBoardSuccessfully = pluginMain.getConfig().getString("String_Message_ScoreBoard_SendBoardSuccessfully");
        //ScoreBoards.end

        //Defaults
        String_Message_Default_win = pluginMain.getConfig().getString("String_Message_Default_win");
        String_Message_Default_to = pluginMain.getConfig().getString("String_Message_Default_to");
        String_Message_Default_welcometo = pluginMain.getConfig().getString("String_Message_Default_welcometo");
        String_Message_Default_haveagoodtime = pluginMain.getConfig().getString("String_Message_Default_haveagoodtime");
        String_Message_Default_Mr_Miss_ = pluginMain.getConfig().getString("String_Message_Default_Mr_Miss_");
        String_Message_Default_Successfully = pluginMain.getConfig().getString("String_Message_Default_Successfully");
        String_Message_Default_Already = pluginMain.getConfig().getString("String_Message_Default_Already");
        //Defaults.end

        //Commands
        String_Message_Command_Tp = pluginMain.getConfig().getString("String_Message_Command_Tp");
        String_Message_Command_ErrorValue = pluginMain.getConfig().getString("String_Message_Command_ErrorValue");
        //Commands.end

        //称号
        String_Called_VIP = pluginMain.getConfig().getString("VIP");
        String_Called_VIPULTRA = pluginMain.getConfig().getString("VIPULTRA");
        String_Called_VIPPRO = pluginMain.getConfig().getString("VIPPRO");
        String_Called_OP = pluginMain.getConfig().getString("OP");
        String_Called_PLUGINMAKER = pluginMain.getConfig().getString("PLUGINMAKER");
    }
}
