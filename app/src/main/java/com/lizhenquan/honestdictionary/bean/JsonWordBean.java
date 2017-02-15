package com.lizhenquan.honestdictionary.bean;

import java.util.List;

/**
 * Created by lizhenquan on 2017/2/12.
 */

public class JsonWordBean {

    /**
     * word : apple
     * pronunciation : {"AmE":"ˈæp(ə)l","AmEmp3":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/e5/43/E543B40FFA2386DBF858F93CA15C72E5.mp3","BrE":"'æpl","BrEmp3":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/george/e5/43/E543B40FFA2386DBF858F93CA15C72E5.mp3"}
     * defs : [{"pos":"n.","def":"苹果公司；【植】苹果；【植】苹果树"},{"pos":"Web","def":"苹果电脑；美国苹果；美国苹果公司"}]
     * sams : [{"eng":"He told her that she was the apple of his eye, that she was wound around his heartstrings.","chn":"他告诉她，她是他的掌上明珠，是他的心头肉。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/2e/e4/2EE4D8C4F185E97F7347342958266ACA.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/2e/e4/2EE4D8C4F185E97F7347342958266ACA.mp4"},{"eng":"\"The apple fell because its stem would no longer hold it to its branch, \" was his first thought.","chn":"\u201c因为苹果的蒂已经不能使苹果继续挂在树枝上，所以它掉下来了。\u201d这是他最初的想法。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/17/8f/178F285298F8DDAFF4C92103E8623C52.mp3","mp4Url":null},{"eng":"He ordered her to shoot at the apple with one of her arrows.","chn":"他命令她用一支箭射苹果。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/59/46/5946CB04E09A76F397D8EB94615E0406.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/59/46/5946CB04E09A76F397D8EB94615E0406.mp4"},{"eng":"How Much Do You Know About Apple History?","chn":"你对苹果历史了解多少？","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/b0/46/B046A9AB52E8B80C313E23713AA99E59.mp3","mp4Url":null},{"eng":"As of press time, Apple stores did not respond on the matter.","chn":"截至发稿时，苹果店未对此事作出回应。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/2a/c7/2AC744A474737C128C961681E836CDD3.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/2a/c7/2AC744A474737C128C961681E836CDD3.mp4"},{"eng":"How much is one apple?","chn":"苹果多少钱一个？","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/eb/d0/EBD025BC6DED7F7D1DD2767ED7E3B7C7.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/eb/d0/EBD025BC6DED7F7D1DD2767ED7E3B7C7.mp4"},{"eng":"The question of income tax was the apple of discord among the senate.","chn":"所得税问题在参议院众议员中引起了争论。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/4d/ed/4DED692297F3CB7738E04A694D3ADD2D.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/4d/ed/4DED692297F3CB7738E04A694D3ADD2D.mp4"},{"eng":"He began shaving an Apple logo into the back of his head.","chn":"他开始在后脑勺剃出有苹果标识的发型。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/b7/b5/B7B5F3220A620FE634AB6AD9855F4F4C.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/b7/b5/B7B5F3220A620FE634AB6AD9855F4F4C.mp4"},{"eng":"My teacher considers Apple Daily is nothing but a tabloid providing superficial news.","chn":"我的老师认为苹果日报不过是提供肤浅报导的八卦报纸。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/30/0e/300EAE1A1519F26C1B7A316248C29F0F.mp3","mp4Url":null},{"eng":"But many analysts assumed that the Times and Apple had already formed some sort of partnership.","chn":"不过，许多分析人士认为，该报与苹果公司已经达成了某些合作。","mp3Url":"https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/5d/a7/5DA7A379F6B51ECE772D3F0A60021D5D.mp3","mp4Url":"https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/5d/a7/5DA7A379F6B51ECE772D3F0A60021D5D.mp4"}]
     */

    private String            word;
    /**
     * AmE : ˈæp(ə)l
     * AmEmp3 : https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/e5/43/E543B40FFA2386DBF858F93CA15C72E5.mp3
     * BrE : 'æpl
     * BrEmp3 : https://dictionary.blob.core.chinacloudapi.cn/media/audio/george/e5/43/E543B40FFA2386DBF858F93CA15C72E5.mp3
     */

    private PronunciationBean pronunciation;
    /**
     * pos : n.
     * def : 苹果公司；【植】苹果；【植】苹果树
     */

    private List<DefsBean>    defs;
    /**
     * eng : He told her that she was the apple of his eye, that she was wound around his heartstrings.
     * chn : 他告诉她，她是他的掌上明珠，是他的心头肉。
     * mp3Url : https://dictionary.blob.core.chinacloudapi.cn/media/audio/tom/2e/e4/2EE4D8C4F185E97F7347342958266ACA.mp3
     * mp4Url : https://dictionary.blob.core.chinacloudapi.cn/media/video/cissy/2e/e4/2EE4D8C4F185E97F7347342958266ACA.mp4
     */

    private List<SamsBean>    sams;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public PronunciationBean getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(PronunciationBean pronunciation) {
        this.pronunciation = pronunciation;
    }

    public List<DefsBean> getDefs() {
        return defs;
    }

    public void setDefs(List<DefsBean> defs) {
        this.defs = defs;
    }

    public List<SamsBean> getSams() {
        return sams;
    }

    public void setSams(List<SamsBean> sams) {
        this.sams = sams;
    }

    public static class PronunciationBean {
        private String AmE;
        private String AmEmp3;
        private String BrE;
        private String BrEmp3;

        public String getAmE() {
            return AmE;
        }

        public void setAmE(String AmE) {
            this.AmE = AmE;
        }

        public String getAmEmp3() {
            return AmEmp3;
        }

        public void setAmEmp3(String AmEmp3) {
            this.AmEmp3 = AmEmp3;
        }

        public String getBrE() {
            return BrE;
        }

        public void setBrE(String BrE) {
            this.BrE = BrE;
        }

        public String getBrEmp3() {
            return BrEmp3;
        }

        public void setBrEmp3(String BrEmp3) {
            this.BrEmp3 = BrEmp3;
        }
    }

    public static class DefsBean {
        private String pos;
        private String def;

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getDef() {
            return def;
        }

        public void setDef(String def) {
            this.def = def;
        }
    }

    public static class SamsBean {
        private String eng;
        private String chn;
        private String mp3Url;
        private String mp4Url;

        public String getEng() {
            return eng;
        }

        public void setEng(String eng) {
            this.eng = eng;
        }

        public String getChn() {
            return chn;
        }

        public void setChn(String chn) {
            this.chn = chn;
        }

        public String getMp3Url() {
            return mp3Url;
        }

        public void setMp3Url(String mp3Url) {
            this.mp3Url = mp3Url;
        }

        public String getMp4Url() {
            return mp4Url;
        }

        public void setMp4Url(String mp4Url) {
            this.mp4Url = mp4Url;
        }
    }
}
