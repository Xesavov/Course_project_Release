package com.orangeteam.NewAuc.services;

import com.orangeteam.NewAuc.enums.AttrType;
import com.orangeteam.NewAuc.models.*;
import com.orangeteam.NewAuc.reps.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeService {
    AttributeRepository attributeRepository;
    AttrValRepository attrValRepository;
    ProductRepository productRepository;
    ProdAttrRepository prodAttrRepository;
    ValueRepository valueRepository;

    public String getAttributesByProduct(Product product) {
        String result = "";
        for (ProdAttr pa : product.getProdAttrs()) {
            result += pa.getAttribute().getName() + ": ";
            for (Value v : pa.getValues()) {
                result += v.getValue() + ", ";
            }
            result += "\n";
        }
        result = result.replace(", \n", "\n");
        return result.trim();
    }

    public void createAttribute(String name, AttrType type) {
        Attribute attribute = new Attribute();
        attribute.setName(name);
        attribute.setType(type);
        attributeRepository.save(attribute);
    }

    public void addValue(Long productId, String attributeName, String value) {
        Product product = productRepository.findById(productId).orElse(null);
        Attribute attribute = attributeRepository.findByName(attributeName);
        if (product == null || attribute == null) {
            return;
        }
        if (attribute.getType()==AttrType.DOUBLE){
            try{
                Double.valueOf(value);
            }catch (Exception e){
                return;
            }
        }
        if (attribute.getType()==AttrType.INTEGER){
            try{
                Integer.valueOf(value);
            }catch (Exception e){
                return;
            }
        }
        if (attribute.getType()==AttrType.SINGLECHOICE || attribute.getType()==AttrType.MULTYPLECHOICE){
            List<AttrVal> allVals = attrValRepository.findByValue(value);
            if(allVals.size()==0) {
                AttrVal attrVal = new AttrVal();
                attrVal.setValue(value);
                attrVal.setAttribute(attribute);
                attrValRepository.save(attrVal);
            }
        }
        ProdAttr prodAttr = prodAttrRepository.findByProductIdAndAttributeId(productId, attribute.getId());
        if(prodAttr==null) {
            prodAttr = new ProdAttr();
            prodAttr.setAttribute(attribute);
            prodAttr.setProduct(product);
            prodAttrRepository.save(prodAttr);
        }
        Value val = new Value();
        val.setProdAttr(prodAttr);
        val.setValue(value);
        valueRepository.save(val);
    }

    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Autowired
    public void setAttrValRepository(AttrValRepository attrValRepository) {
        this.attrValRepository = attrValRepository;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProdAttrRepository(ProdAttrRepository prodAttrRepository) {
        this.prodAttrRepository = prodAttrRepository;
    }

    @Autowired
    public void setValueRepository(ValueRepository valueRepository) {
        this.valueRepository = valueRepository;
    }
}
