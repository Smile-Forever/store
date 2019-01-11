package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/category")
public class GoodsCategoryController extends BaseController{
	
	@Autowired
	private IGoodsCategoryService categoryService;
	
	@RequestMapping("/list/{parentId}")
	public ResponseResult<List<GoodsCategory>> getByParent(@PathVariable("parentId")Long parentId){
		
		List<GoodsCategory> data = categoryService.getByParentId(parentId);
		
		return new ResponseResult<List<GoodsCategory>>(SUCCESS , data);
	}
}
