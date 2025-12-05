package com.study.mf.dto.wrapper;

import com.study.mf.dto.MusicDTO;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

@XmlRootElement(name = "PagedModel")
public class MusicPagedModelWrapper extends PagedModel<EntityModel<MusicDTO>> {
}
