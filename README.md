#概述
这个库可以帮助你更方便地实现新手指引等全屏遮罩、部分透明或突出的效果。
如有任何使用上的问题，请直接发起Issue即可，如有新功能建议或bug修复，也欢迎Fork/PullToRequest：)
#FeaturedShowcaseView
Use this library to customize any showcase effects! 
#Usages
```java
        //Show defaults
        ShowConfig config = new ShowConfig.Builder()
                .adjustToTarget(true)
                .maskColor(Color.parseColor("#99000000"))
                .hideOnTouchOutside(false)
                .shapePadding(new Padding(0))
                .target(new ViewTarget(btnDemo))
                .targetDrawer(new CircleShape())
//                .targetDrawer(new RectangleShape())
//                .targetDrawer(new RoundedRectShape(5))
//                .targetDrawer(new OvalShape())
                .showcaseListener(null)
                .build();
        FeaturedShowcaseView.show(this, config);
```
For more details , please see ShowConfig class.
#Licence
Apache Licence 2.0
#Contact 
Email: muyangmin@foxmail.com
