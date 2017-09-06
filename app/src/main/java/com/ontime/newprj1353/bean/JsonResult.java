package com.ontime.newprj1353.bean;

import java.util.List;

/**
 * Created by shgl1hz1 on 2017/8/22.
 */

public class JsonResult {

    /**
     * data : {"CompanyName":"Nike专营","CompanyID":1,"shops":[{"majorClasses":[{"majorClassesID":1,"majorClassesName":"数码","middessClasses":[{"generalClasses":[{"generalClassesID":3,"generalClassesName":"Apple苹果","products":[{"adviseProductCode1":"","adviseProductCode2":"","adviseProductCode3":"","audioList":[],"colors":[{"colorId":5734,"name":"3289c7","prictures":[{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}],"desc":"蓝色"}],"detial":"313123","faver":3.5,"imageFileLocalPath":"upload/product/1/2017/8/18/1014148680.jpg","imageFileName":"1014148680.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg","logoImageLocalPath":"upload/LOGO/1/1026493440.png","logoImageName":"1026493440.png","logoImageURL":"http://139.224.104.4:8085/iknow/upload/LOGO/1/1026493440.png","minutiaes":[],"paramaters":[],"price":123,"productCode":"12312312","productID":293,"productName":"手机","productSort":1,"productURL":"sds","size":"8,9,10,11","tagList":[],"unit":""}]}],"middleClassesID":2,"middleClassesName":"手机"}]}],"ShopName":"Galeries Lafayette","PosId":59,"ShopID":1}]}
     * msgInfo :
     * result : OK
     */

    private DataBean data;
    private String msgInfo;
    private String result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class DataBean {
        /**
         * CompanyName : Nike专营
         * CompanyID : 1
         * shops : [{"majorClasses":[{"majorClassesID":1,"majorClassesName":"数码","middessClasses":[{"generalClasses":[{"generalClassesID":3,"generalClassesName":"Apple苹果","products":[{"adviseProductCode1":"","adviseProductCode2":"","adviseProductCode3":"","audioList":[],"colors":[{"colorId":5734,"name":"3289c7","prictures":[{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}],"desc":"蓝色"}],"detial":"313123","faver":3.5,"imageFileLocalPath":"upload/product/1/2017/8/18/1014148680.jpg","imageFileName":"1014148680.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg","logoImageLocalPath":"upload/LOGO/1/1026493440.png","logoImageName":"1026493440.png","logoImageURL":"http://139.224.104.4:8085/iknow/upload/LOGO/1/1026493440.png","minutiaes":[],"paramaters":[],"price":123,"productCode":"12312312","productID":293,"productName":"手机","productSort":1,"productURL":"sds","size":"8,9,10,11","tagList":[],"unit":""}]}],"middleClassesID":2,"middleClassesName":"手机"}]}],"ShopName":"Galeries Lafayette","PosId":59,"ShopID":1}]
         */

        private String CompanyName;
        private int CompanyID;
        private List<ShopsBean> shops;

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public int getCompanyID() {
            return CompanyID;
        }

        public void setCompanyID(int CompanyID) {
            this.CompanyID = CompanyID;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public static class ShopsBean {
            /**
             * majorClasses : [{"majorClassesID":1,"majorClassesName":"数码","middessClasses":[{"generalClasses":[{"generalClassesID":3,"generalClassesName":"Apple苹果","products":[{"adviseProductCode1":"","adviseProductCode2":"","adviseProductCode3":"","audioList":[],"colors":[{"colorId":5734,"name":"3289c7","prictures":[{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}],"desc":"蓝色"}],"detial":"313123","faver":3.5,"imageFileLocalPath":"upload/product/1/2017/8/18/1014148680.jpg","imageFileName":"1014148680.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg","logoImageLocalPath":"upload/LOGO/1/1026493440.png","logoImageName":"1026493440.png","logoImageURL":"http://139.224.104.4:8085/iknow/upload/LOGO/1/1026493440.png","minutiaes":[],"paramaters":[],"price":123,"productCode":"12312312","productID":293,"productName":"手机","productSort":1,"productURL":"sds","size":"8,9,10,11","tagList":[],"unit":""}]}],"middleClassesID":2,"middleClassesName":"手机"}]}]
             * ShopName : Galeries Lafayette
             * PosId : 59
             * ShopID : 1
             */

            private String ShopName;
            private int PosId;
            private int ShopID;
            private List<MajorClassesBean> majorClasses;

            public String getShopName() {
                return ShopName;
            }

            public void setShopName(String ShopName) {
                this.ShopName = ShopName;
            }

            public int getPosId() {
                return PosId;
            }

            public void setPosId(int PosId) {
                this.PosId = PosId;
            }

            public int getShopID() {
                return ShopID;
            }

            public void setShopID(int ShopID) {
                this.ShopID = ShopID;
            }

            public List<MajorClassesBean> getMajorClasses() {
                return majorClasses;
            }

            public void setMajorClasses(List<MajorClassesBean> majorClasses) {
                this.majorClasses = majorClasses;
            }

            public static class MajorClassesBean {
                /**
                 * majorClassesID : 1
                 * majorClassesName : 数码
                 * middessClasses : [{"generalClasses":[{"generalClassesID":3,"generalClassesName":"Apple苹果","products":[{"adviseProductCode1":"","adviseProductCode2":"","adviseProductCode3":"","audioList":[],"colors":[{"colorId":5734,"name":"3289c7","prictures":[{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}],"desc":"蓝色"}],"detial":"313123","faver":3.5,"imageFileLocalPath":"upload/product/1/2017/8/18/1014148680.jpg","imageFileName":"1014148680.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg","logoImageLocalPath":"upload/LOGO/1/1026493440.png","logoImageName":"1026493440.png","logoImageURL":"http://139.224.104.4:8085/iknow/upload/LOGO/1/1026493440.png","minutiaes":[],"paramaters":[],"price":123,"productCode":"12312312","productID":293,"productName":"手机","productSort":1,"productURL":"sds","size":"8,9,10,11","tagList":[],"unit":""}]}],"middleClassesID":2,"middleClassesName":"手机"}]
                 */

                private int majorClassesID;
                private String majorClassesName;
                private List<MiddessClassesBean> middessClasses;

                public int getMajorClassesID() {
                    return majorClassesID;
                }

                public void setMajorClassesID(int majorClassesID) {
                    this.majorClassesID = majorClassesID;
                }

                public String getMajorClassesName() {
                    return majorClassesName;
                }

                public void setMajorClassesName(String majorClassesName) {
                    this.majorClassesName = majorClassesName;
                }

                public List<MiddessClassesBean> getMiddessClasses() {
                    return middessClasses;
                }

                public void setMiddessClasses(List<MiddessClassesBean> middessClasses) {
                    this.middessClasses = middessClasses;
                }

                public static class MiddessClassesBean {
                    /**
                     * generalClasses : [{"generalClassesID":3,"generalClassesName":"Apple苹果","products":[{"adviseProductCode1":"","adviseProductCode2":"","adviseProductCode3":"","audioList":[],"colors":[{"colorId":5734,"name":"3289c7","prictures":[{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}],"desc":"蓝色"}],"detial":"313123","faver":3.5,"imageFileLocalPath":"upload/product/1/2017/8/18/1014148680.jpg","imageFileName":"1014148680.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg","logoImageLocalPath":"upload/LOGO/1/1026493440.png","logoImageName":"1026493440.png","logoImageURL":"http://139.224.104.4:8085/iknow/upload/LOGO/1/1026493440.png","minutiaes":[],"paramaters":[],"price":123,"productCode":"12312312","productID":293,"productName":"手机","productSort":1,"productURL":"sds","size":"8,9,10,11","tagList":[],"unit":""}]}]
                     * middleClassesID : 2
                     * middleClassesName : 手机
                     */

                    private int middleClassesID;
                    private String middleClassesName;
                    private List<GeneralClassesBean> generalClasses;

                    public int getMiddleClassesID() {
                        return middleClassesID;
                    }

                    public void setMiddleClassesID(int middleClassesID) {
                        this.middleClassesID = middleClassesID;
                    }

                    public String getMiddleClassesName() {
                        return middleClassesName;
                    }

                    public void setMiddleClassesName(String middleClassesName) {
                        this.middleClassesName = middleClassesName;
                    }

                    public List<GeneralClassesBean> getGeneralClasses() {
                        return generalClasses;
                    }

                    public void setGeneralClasses(List<GeneralClassesBean> generalClasses) {
                        this.generalClasses = generalClasses;
                    }

                    public static class GeneralClassesBean {
                        /**
                         * generalClassesID : 3
                         * generalClassesName : Apple苹果
                         * products : [{"adviseProductCode1":"","adviseProductCode2":"","adviseProductCode3":"","audioList":[],"colors":[{"colorId":5734,"name":"3289c7","prictures":[{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}],"desc":"蓝色"}],"detial":"313123","faver":3.5,"imageFileLocalPath":"upload/product/1/2017/8/18/1014148680.jpg","imageFileName":"1014148680.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg","logoImageLocalPath":"upload/LOGO/1/1026493440.png","logoImageName":"1026493440.png","logoImageURL":"http://139.224.104.4:8085/iknow/upload/LOGO/1/1026493440.png","minutiaes":[],"paramaters":[],"price":123,"productCode":"12312312","productID":293,"productName":"手机","productSort":1,"productURL":"sds","size":"8,9,10,11","tagList":[],"unit":""}]
                         */

                        private int generalClassesID;
                        private String generalClassesName;
                        private List<ProductsBean> products;

                        public int getGeneralClassesID() {
                            return generalClassesID;
                        }

                        public void setGeneralClassesID(int generalClassesID) {
                            this.generalClassesID = generalClassesID;
                        }

                        public String getGeneralClassesName() {
                            return generalClassesName;
                        }

                        public void setGeneralClassesName(String generalClassesName) {
                            this.generalClassesName = generalClassesName;
                        }

                        public List<ProductsBean> getProducts() {
                            return products;
                        }

                        public void setProducts(List<ProductsBean> products) {
                            this.products = products;
                        }

                        public static class ProductsBean {
                            /**
                             * adviseProductCode1 :
                             * adviseProductCode2 :
                             * adviseProductCode3 :
                             * audioList : []
                             * colors : [{"colorId":5734,"name":"3289c7","prictures":[{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}],"desc":"蓝色"}]
                             * detial : 313123
                             * faver : 3.5
                             * imageFileLocalPath : upload/product/1/2017/8/18/1014148680.jpg
                             * imageFileName : 1014148680.jpg
                             * imageFileURL : http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg
                             * logoImageLocalPath : upload/LOGO/1/1026493440.png
                             * logoImageName : 1026493440.png
                             * logoImageURL : http://139.224.104.4:8085/iknow/upload/LOGO/1/1026493440.png
                             * minutiaes : []
                             * paramaters : []
                             * price : 123
                             * productCode : 12312312
                             * productID : 293
                             * productName : 手机
                             * productSort : 1
                             * productURL : sds
                             * size : 8,9,10,11
                             * tagList : []
                             * unit :
                             */

                            private String adviseProductCode1;
                            private String adviseProductCode2;
                            private String adviseProductCode3;
                            private String detial;
                            private double faver;
                            private String imageFileLocalPath;
                            private String imageFileName;
                            private String imageFileURL;
                            private String logoImageLocalPath;
                            private String logoImageName;
                            private String logoImageURL;
                            private int price;
                            private String productCode;
                            private int productID;
                            private String productName;
                            private int productSort;
                            private String productURL;
                            private String size;
                            private String unit;
                            private List<?> audioList;
                            private List<ColorsBean> colors;
                            private List<?> minutiaes;
                            private List<?> paramaters;
                            private List<?> tagList;

                            public String getAdviseProductCode1() {
                                return adviseProductCode1;
                            }

                            public void setAdviseProductCode1(String adviseProductCode1) {
                                this.adviseProductCode1 = adviseProductCode1;
                            }

                            public String getAdviseProductCode2() {
                                return adviseProductCode2;
                            }

                            public void setAdviseProductCode2(String adviseProductCode2) {
                                this.adviseProductCode2 = adviseProductCode2;
                            }

                            public String getAdviseProductCode3() {
                                return adviseProductCode3;
                            }

                            public void setAdviseProductCode3(String adviseProductCode3) {
                                this.adviseProductCode3 = adviseProductCode3;
                            }

                            public String getDetial() {
                                return detial;
                            }

                            public void setDetial(String detial) {
                                this.detial = detial;
                            }

                            public double getFaver() {
                                return faver;
                            }

                            public void setFaver(double faver) {
                                this.faver = faver;
                            }

                            public String getImageFileLocalPath() {
                                return imageFileLocalPath;
                            }

                            public void setImageFileLocalPath(String imageFileLocalPath) {
                                this.imageFileLocalPath = imageFileLocalPath;
                            }

                            public String getImageFileName() {
                                return imageFileName;
                            }

                            public void setImageFileName(String imageFileName) {
                                this.imageFileName = imageFileName;
                            }

                            public String getImageFileURL() {
                                return imageFileURL;
                            }

                            public void setImageFileURL(String imageFileURL) {
                                this.imageFileURL = imageFileURL;
                            }

                            public String getLogoImageLocalPath() {
                                return logoImageLocalPath;
                            }

                            public void setLogoImageLocalPath(String logoImageLocalPath) {
                                this.logoImageLocalPath = logoImageLocalPath;
                            }

                            public String getLogoImageName() {
                                return logoImageName;
                            }

                            public void setLogoImageName(String logoImageName) {
                                this.logoImageName = logoImageName;
                            }

                            public String getLogoImageURL() {
                                return logoImageURL;
                            }

                            public void setLogoImageURL(String logoImageURL) {
                                this.logoImageURL = logoImageURL;
                            }

                            public int getPrice() {
                                return price;
                            }

                            public void setPrice(int price) {
                                this.price = price;
                            }

                            public String getProductCode() {
                                return productCode;
                            }

                            public void setProductCode(String productCode) {
                                this.productCode = productCode;
                            }

                            public int getProductID() {
                                return productID;
                            }

                            public void setProductID(int productID) {
                                this.productID = productID;
                            }

                            public String getProductName() {
                                return productName;
                            }

                            public void setProductName(String productName) {
                                this.productName = productName;
                            }

                            public int getProductSort() {
                                return productSort;
                            }

                            public void setProductSort(int productSort) {
                                this.productSort = productSort;
                            }

                            public String getProductURL() {
                                return productURL;
                            }

                            public void setProductURL(String productURL) {
                                this.productURL = productURL;
                            }

                            public String getSize() {
                                return size;
                            }

                            public void setSize(String size) {
                                this.size = size;
                            }

                            public String getUnit() {
                                return unit;
                            }

                            public void setUnit(String unit) {
                                this.unit = unit;
                            }

                            public List<?> getAudioList() {
                                return audioList;
                            }

                            public void setAudioList(List<?> audioList) {
                                this.audioList = audioList;
                            }

                            public List<ColorsBean> getColors() {
                                return colors;
                            }

                            public void setColors(List<ColorsBean> colors) {
                                this.colors = colors;
                            }

                            public List<?> getMinutiaes() {
                                return minutiaes;
                            }

                            public void setMinutiaes(List<?> minutiaes) {
                                this.minutiaes = minutiaes;
                            }

                            public List<?> getParamaters() {
                                return paramaters;
                            }

                            public void setParamaters(List<?> paramaters) {
                                this.paramaters = paramaters;
                            }

                            public List<?> getTagList() {
                                return tagList;
                            }

                            public void setTagList(List<?> tagList) {
                                this.tagList = tagList;
                            }

                            public static class ColorsBean {
                                /**
                                 * colorId : 5734
                                 * name : 3289c7
                                 * prictures : [{"imageFileId":1292,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_929.jpg","imageFileName":"20170818101410_929.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg"},{"imageFileId":1293,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_639.jpg","imageFileName":"20170818101410_639.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_639.jpg"},{"imageFileId":1294,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_607.jpg","imageFileName":"20170818101410_607.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_607.jpg"},{"imageFileId":1295,"imageFileLocalPath":"upload/all/1/2017/8/18/20170818101410_406.jpg","imageFileName":"20170818101410_406.jpg","imageFileURL":"http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_406.jpg"}]
                                 * desc : 蓝色
                                 */
                                private int colorId;
                                private String name;
                                private String desc;
                                private List<PricturesBean> prictures;

                                public int getColorId() {
                                    return colorId;
                                }

                                public void setColorId(int colorId) {
                                    this.colorId = colorId;
                                }

                                public String getName() {
                                    return name;
                                }

                                public void setName(String name) {
                                    this.name = name;
                                }

                                public String getDesc() {
                                    return desc;
                                }

                                public void setDesc(String desc) {
                                    this.desc = desc;
                                }

                                public List<PricturesBean> getPrictures() {
                                    return prictures;
                                }

                                public void setPrictures(List<PricturesBean> prictures) {
                                    this.prictures = prictures;
                                }

                                public static class PricturesBean {
                                    /**
                                     * imageFileId : 1292
                                     * imageFileLocalPath : upload/all/1/2017/8/18/20170818101410_929.jpg
                                     * imageFileName : 20170818101410_929.jpg
                                     * imageFileURL : http://139.224.104.4:8085/iknow/upload/all/1/2017/8/18/20170818101410_929.jpg
                                     */

                                    private int imageFileId;
                                    private String imageFileLocalPath;
                                    private String imageFileName;
                                    private String imageFileURL;

                                    public int getImageFileId() {
                                        return imageFileId;
                                    }

                                    public void setImageFileId(int imageFileId) {
                                        this.imageFileId = imageFileId;
                                    }

                                    public String getImageFileLocalPath() {
                                        return imageFileLocalPath;
                                    }

                                    public void setImageFileLocalPath(String imageFileLocalPath) {
                                        this.imageFileLocalPath = imageFileLocalPath;
                                    }

                                    public String getImageFileName() {
                                        return imageFileName;
                                    }

                                    public void setImageFileName(String imageFileName) {
                                        this.imageFileName = imageFileName;
                                    }

                                    public String getImageFileURL() {
                                        return imageFileURL;
                                    }

                                    public void setImageFileURL(String imageFileURL) {
                                        this.imageFileURL = imageFileURL;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
