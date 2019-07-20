package ph.net.see.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.micronaut.context.annotation.Context;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import ph.net.see.config.jackson.DefaultPageMixin;
import ph.net.see.config.jackson.OrderMixin;
import ph.net.see.config.jackson.PageableMixin;
import ph.net.see.config.jackson.SortMixin;

import javax.inject.Inject;

@Context
public class JacksonConfig {

    @Inject
    public void configureObjectMapper(ObjectMapper objectMapper) throws ClassNotFoundException {
        Class<?> defaultPageClass = Class.forName("io.micronaut.data.model.DefaultPage");

        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(Page.class, (Class) defaultPageClass);

        SimpleModule module = new SimpleModule("CustomModel", Version.unknownVersion());
        module.setAbstractTypes(resolver);

        objectMapper.registerModule(module);

        // Note: We cannot use Page.of(..) as @JsonCreator because of
        // https://github.com/FasterXML/jackson-databind/issues/2384
        objectMapper.addMixIn(defaultPageClass, DefaultPageMixin.class);
        objectMapper.addMixIn(Pageable.class, PageableMixin.class);
        objectMapper.addMixIn(Sort.class, SortMixin.class);
        objectMapper.addMixIn(Sort.Order.class, OrderMixin.class);
    }
}
