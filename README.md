# small-utils

日常用到的一些工具集合

## 1、tidy-media
整理 ~/Media文件夹, 使其:
* 1. 所有的压缩包进行解压, 并删除压缩包
* 2. 所有的视频 移动到 ~/Media 目录下
* 3. 所有的种子 移动到 ~/Media/1-torrent 文件下
* 4. 所有的图片 移动到 ~/Media/2-picture 文件下
* 5. 除了以上这几个特殊文件夹外, ~/Media下其他所有的文件夹都删除

## 2、csdn-blog-migration-crawler
CSDN博客迁移，把CSDN博客上的内容爬取下来，存为本地的md文件，方便部署到hexo。

具体需求如下：
* 1. 博文内容要转换成MarkDown源码格式存储。
* 2. 支持CSDN博客内容是MarkDown渲染或者非MarkDown渲染的。
* 3. 除了文章正文，其他信息（如标题、发布时间、分类、标签、是否转载等）也要保存下来。
* 4. 生成文章描述。