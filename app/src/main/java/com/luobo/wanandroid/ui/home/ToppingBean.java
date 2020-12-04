package com.luobo.wanandroid.ui.home;

import java.util.List;

public class ToppingBean {

    /**
     * data : [{"apkLink":"","audit":1,"author":"扔物线","canEdit":false,"chapterId":249,"chapterName":"干货资源","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12554,"link":"https://www.bilibili.com/video/BV1CA41177Se","niceDate":"2020-11-30 00:00","niceShareDate":"2020-03-23 16:36","origin":"","prefix":"","projectLink":"","publishTime":1606665600000,"realSuperChapterId":248,"selfVisible":0,"shareDate":1584952597000,"shareUser":"","superChapterId":249,"superChapterName":"干货资源","tags":[],"title":"Android 面试黑洞&mdash;&mdash;当我按下 Home 键再切回来，会发生什么？","type":1,"userId":-1,"visible":1,"zan":0},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>大家在日常开发过程中，应该对invalidate，requestLayout简易的区别都理解，更近准备针对这两个问题问几问。<\/p>\r\n<p>先来invalidate吧。<\/p>\r\n<ol>\r\n<li>invalidate 的执行流程是什么样子的？<\/li>\r\n<li>如果连续调用多次invalidate执行流程是？<\/li>\r\n<li>invalidate重绘的区域是如何确定的，仅是当前View还是整个View树，还是某种计算方式？<\/li>\r\n<li>View除了invalidate()外，还有个方法叫做postInvalidateOnAnimation()，这两个方法有何区别？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":16190,"link":"https://wanandroid.com/wenda/show/16190","niceDate":"2020-11-24 13:32","niceShareDate":"2020-11-24 13:32","origin":"","prefix":"","projectLink":"","publishTime":1606195950000,"realSuperChapterId":439,"selfVisible":0,"shareDate":1606195930000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}],"title":"每日一问 | View invalidate() 相关的一些细节探究~","type":1,"userId":2,"visible":1,"zan":4},{"apkLink":"","audit":1,"author":"xiaoyang","canEdit":false,"chapterId":440,"chapterName":"官方","collect":false,"courseId":13,"desc":"<p>我们在写代码的时候，有时候很容易使用一个高版本的 API，如果不注意，可能会导致在一些低版本的设备崩溃。<\/p>\r\n<p>因此，我们可以选择引入 lint 在编译时进行检查。<\/p>\r\n<p>今天的问题是？<\/p>\r\n<ol>\r\n<li>应用：如何在打包时，强制开启 New API检查？<\/li>\r\n<li>原理：lint 怎么能知道某个 方法是哪个版本加入的？是有一个汇总的地方维护着这样的方法列表吗？<\/li>\r\n<li>原理：即使有这样的一个列表，lint是怎么扫描每一个方法调用的？<\/li>\r\n<\/ol>","descMd":"","envelopePic":"","fresh":false,"id":15949,"link":"https://www.wanandroid.com/wenda/show/15949","niceDate":"2020-11-02 00:15","niceShareDate":"2020-11-02 00:14","origin":"","prefix":"","projectLink":"","publishTime":1604247343000,"realSuperChapterId":439,"selfVisible":0,"shareDate":1604247264000,"shareUser":"","superChapterId":440,"superChapterName":"问答","tags":[{"name":"本站发布","url":"/article/list/0?cid=440"},{"name":"问答","url":"/wenda"}],"title":"每日一问 | Call requires API level 23 (current min is 14) 扫描出来的原理是？","type":1,"userId":2,"visible":1,"zan":9}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private List<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * apkLink :
         * audit : 1
         * author : 扔物线
         * canEdit : false
         * chapterId : 249
         * chapterName : 干货资源
         * collect : false
         * courseId : 13
         * desc :
         * descMd :
         * envelopePic :
         * fresh : false
         * id : 12554
         * link : https://www.bilibili.com/video/BV1CA41177Se
         * niceDate : 2020-11-30 00:00
         * niceShareDate : 2020-03-23 16:36
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1606665600000
         * realSuperChapterId : 248
         * selfVisible : 0
         * shareDate : 1584952597000
         * shareUser :
         * superChapterId : 249
         * superChapterName : 干货资源
         * tags : []
         * title : Android 面试黑洞&mdash;&mdash;当我按下 Home 键再切回来，会发生什么？
         * type : 1
         * userId : -1
         * visible : 1
         * zan : 0
         */

        private String apkLink;
        private int audit;
        private String author;
        private boolean canEdit;
        private int chapterId;
        private String chapterName;
        private boolean collect;
        private int courseId;
        private String desc;
        private String descMd;
        private String envelopePic;
        private boolean fresh;
        private int id;
        private String link;
        private String niceDate;
        private String niceShareDate;
        private String origin;
        private String prefix;
        private String projectLink;
        private long publishTime;
        private int realSuperChapterId;
        private int selfVisible;
        private long shareDate;
        private String shareUser;
        private int superChapterId;
        private String superChapterName;
        private String title;
        private int type;
        private int userId;
        private int visible;
        private int zan;
        private List<?> tags;

        public String getApkLink() {
            return apkLink;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public boolean isCollect() {
            return collect;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDescMd() {
            return descMd;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public boolean isFresh() {
            return fresh;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public long getShareDate() {
            return shareDate;
        }

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }

        public String getShareUser() {
            return shareUser;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public List<?> getTags() {
            return tags;
        }

        public void setTags(List<?> tags) {
            this.tags = tags;
        }
    }
}
