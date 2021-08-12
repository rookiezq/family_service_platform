# Ant Design Vue表格中添加日期选择框

> 环境
>
> 模板：Ant Design Vue 官网 https://antdv.com/

## 需求

需求如标题介绍



## 完成效果

一开始就是很普通的表格，日期那里是文本框

最后完成效果如图

![image-20210809214707989](https://demon-1258469613.cos.ap-shanghai.myqcloud.com/img/image-20210809214707989.png)

## 原始代码

原始代码见官网[表格-可编辑行](https://antdv.com/components/table-cn/#components-table-demo-editable-rows)，我这里使用我自己的代码，整体是差不多的

简单概括一下，可以分为这么几步

1. 这个是表格模板，columns为单元格对象数组（包含标题、属性等），dataSource为数据源

    ```vue
    <a-table :columns="columns" :dataSource="data" bordered align="center">
    ```

    

2. 其中定义了一个template，然后通过v-for的方式循环生成各个列，**列名col**为v-for后跟着的数组

    ```vue
    <template
              v-for="col in [
                'buildingCode',
                'buildingName',
                'unitCount',
                'overRoofDate',
                'finishDate',
                 //其他字段
              ]"
              :slot="col"
              slot-scope="text, record"
    >
    ```

3. 使用一个div作为一整行的容器

    ```vue
    <div :key="col">
    ```

4. 里面用v-if**是否处于编辑模式**做了判断是**文本框**还是**普通单元格**

    ```vue
    <a-input
             v-if="record.editable"
             style="margin: -5px 0"
             :value="text"
             @change="e => handleChange(e.target.value, record.key, col)"
             />
    <template v-else>
    {{ text }}
    </template>
    ```

## 实现

### 日期选择框

先在官网中找到[日期选择框 ](https://antdv.com/components/date-picker-cn/)~~我丢，一开始找了在官网找了半天，搜的是日历，然后把一整个日历丢了进去，效果自己脑补~~

我们使用最简单的选择器，onChange中参数date是moment（框架的对象），dateString为实际选到的值

```vue
<template>
  <div>
    <a-date-picker @change="onChange" />
    <br />
  </div>
</template>
<script>
export default {
  methods: {
    onChange(date, dateString) {
      console.log(date, dateString);
    },
  },
};
</script>
```



### 判断当前单元格是否是日期

由于生产列的方式是用的v-for，所以所有的列都是一个样，上面说的columns中虽然每个列有自己的属性，但是并没有可以自定义嵌入template

所以我们需要在其中做v-if的判断

1. 先判断不可编辑，则是普通单元格
2. 如果是可编辑并且当前列是overRoofDate或者finishDate其中一个，则使用**日期选择器**
3. 最后则是可编辑文本框

```vue
<div :key="col">
    <template v-if="!record.editable">
{{ text }}
    </template>
    <template v-else-if="record.editable && (col==='overRoofDate' || col==='finishDate')">
<div>
    <a-date-picker @change="onChange"/>
        </div>
    </template>
    <a-input
             v-else
             style="margin: -5px 0"
             :value="text"

             @change="e => handleChange(e.target.value, record.key, col)"
             />
</div>
```



当我满心欢喜以为做完的时候，尝试修改后，发现其他列都能保存下来，唯独这两个日期无法获取。

可能有聪明的程序猿猜到了：*"啊！你个大傻逼，onChange里就打印了一下，怎么可能能改成功"*。

雀食，我也知道问题是在这里，苦于对Vue的一知半解，**获取当前日期容易，但是怎么知道当前日期是哪个列的呢？**

直到我看到了这个方法，这个函数是当单元格发生变化时，将修改值缓存下来

见名知意，3个参数分别为单元格值，行键，列名

```js
handleChange(value, key, column) {
    const newData = [...this.data]
    const target = newData.filter(item => key === item.key)[0]
    if (target) {
        target[column] = value
        this.data = newData
    }
}
```

看看调用

```vue
<a-input
     v-else
         style="margin: -5px 0"
         :value="text"
         @change="e => handleChange(e.target.value, record.key, col)"
         />
```

### 传入日期对应的列名

然后看到官网的这个[DatePicker 事件](https://antdv.com/components/date-picker-cn/#DatePicker 事件)

| 事件名称 | 说明               | 回调参数                                             |
| :------- | :----------------- | :--------------------------------------------------- |
| change   | 时间发生变化的回调 | function(date: moment \| string, dateString: string) |

于是照猫画虎

```js
dateChange(date, dateString, key, column) {
    console.log(date, dateString, key, column)
    // 调用handleChange，保存到缓存
    this.handleChange(dateString, key, column)
}
```

```vue
<template v-else-if="record.editable && (col==='overRoofDate' || col==='finishDate')">
<div>
    <a-date-picker @change="(moment,dateString) => dateChange(moment, dateString, record.key, col)"/>
    </div>
</template>
```

再次打印

```
Moment {…} "2021-08-03" "73" "finishDate"
```

感动，大功告成。

这里最让我印象深刻的就是change的回调函数，之前一直都看不太懂，现在有了进一步认识。
