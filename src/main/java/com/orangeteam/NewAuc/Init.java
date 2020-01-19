package com.orangeteam.NewAuc;

import com.orangeteam.NewAuc.enums.AttrType;
import com.orangeteam.NewAuc.enums.ProductStatus;
import com.orangeteam.NewAuc.enums.Role;
import com.orangeteam.NewAuc.models.*;
import com.orangeteam.NewAuc.reps.*;
import com.orangeteam.NewAuc.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class Init {
    private UserDescRepository userDescRepository;
    private PasswordEncoder passwordEncoder;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private CatProdRepository catProdRepository;
    private ImageRepository imageRepository;
    private AttributeRepository attributeRepository;
    private AttributeService attributeService;

    @PostConstruct
    void init() {
        initUsers();
        initCategories();
        initProducts();
        initAttributes();
        initAttrVals();
    }

    void initUsers() {
        Set<Role> user = new HashSet<>();
        user.add(Role.ROLE_USER);
        UserDesc xesavov = new UserDesc();
        xesavov.setLogin("Xesavov");
        xesavov.setPassword(passwordEncoder.encode("user"));
        xesavov.setPhone("8(800)555-35-35");
        xesavov.setEmail("motik@gmail.com");
        xesavov.setFullName("Захаров Владимир Сергеевич");
        xesavov.setRoles(user);
        xesavov.setActivity(true);
        userDescRepository.save(xesavov);
        UserDesc mariti = new UserDesc();
        mariti.setLogin("mariti8");
        mariti.setPassword(passwordEncoder.encode("user"));
        mariti.setPhone("8(800)796-34-22");
        mariti.setEmail("mariti@orange.com");
        mariti.setFullName("Тимонина Мария Андреевна");
        mariti.setRoles(user);
        mariti.setActivity(true);
        userDescRepository.save(mariti);
        Set<Role> ttnk = new HashSet<>();
        ttnk.add(Role.ROLE_TETENKA);
        UserDesc tetenka = new UserDesc(
                null,
                "tetenka",
                passwordEncoder.encode("user"),
                "",
                "",
                "",
                true,
                ttnk,
                null);
        userDescRepository.save(tetenka);
    }

    public void initCategories() {
        Category cats[] = new Category[172];
        cats[1] = new Category("Все товары", null);

        cats[2] = new Category("Аксессуары", cats[1]);
        cats[3] = new Category("Для рук", cats[2]);
        cats[4] = new Category("Для волос", cats[2]);
        cats[5] = new Category("Для одежды", cats[2]);
        cats[6] = new Category("Для шеи", cats[2]);
        cats[7] = new Category("Серьги и клипсы", cats[2]);
        cats[8] = new Category("Часы и аксессуары к ним", cats[2]);
        cats[9] = new Category("Другое", cats[2]);

        cats[10] = new Category("Живопись, графика, гравюра", cats[1]);
        cats[11] = new Category("Живопись", cats[10]);
        cats[12] = new Category("Гравюра", cats[10]);
        cats[13] = new Category("Графика", cats[10]);
        cats[14] = new Category("Книжная", cats[13]);
        cats[15] = new Category("Выпуклая", cats[13]);
        cats[16] = new Category("Углубленная", cats[13]);
        cats[17] = new Category("Плоская", cats[13]);
        cats[18] = new Category("Цветная", cats[13]);
        cats[19] = new Category("Другое", cats[10]);

        cats[20] = new Category("Букинистика", cats[1]);
        cats[21] = new Category("Печатное", cats[20]);
        cats[22] = new Category("Книги", cats[21]);
        cats[23] = new Category("Календари", cats[21]);
        cats[24] = new Category("Фото", cats[21]);
        cats[25] = new Category("Карты", cats[21]);
        cats[26] = new Category("Банкноты", cats[21]);
        cats[27] = new Category("Другое", cats[21]);
        cats[28] = new Category("Рукописное", cats[20]);
        cats[29] = new Category("Рукописи", cats[28]);
        cats[30] = new Category("Ноты", cats[28]);
        cats[31] = new Category("Ценные бумаги", cats[28]);
        cats[32] = new Category("Письма", cats[28]);
        cats[33] = new Category("Другое", cats[28]);
        cats[34] = new Category("Плакаты", cats[20]);
        cats[35] = new Category("Другое", cats[20]);

        cats[36] = new Category("Часы", cats[1]);
        cats[37] = new Category("Напольные", cats[36]);
        cats[38] = new Category("Настенные", cats[36]);
        cats[39] = new Category("Наручные", cats[36]);
        cats[40] = new Category("Карманные", cats[36]);
        cats[41] = new Category("Аксессуары к часам", cats[36]);
        cats[42] = new Category("Другое", cats[36]);

        cats[43] = new Category("Ювелирные изделия", cats[1]);
        cats[44] = new Category("Предметы интерьера", cats[43]);
        cats[45] = new Category("Предметы личного туалета", cats[43]);
        cats[46] = new Category("Сервировка стола", cats[43]);
        cats[47] = new Category("Столовые приборы", cats[46]);
        cats[48] = new Category("Столовая посуда", cats[46]);
        cats[49] = new Category("Украшения оружия", cats[43]);
        cats[50] = new Category("Для свершения обрядов", cats[43]);
        cats[51] = new Category("Монеты, медали, ордена, памятные знаки", cats[43]);
        cats[52] = new Category("Предметы личных украшений", cats[43]);
        cats[53] = new Category("Для рук", cats[52]);
        cats[54] = new Category("Для волос", cats[52]);
        cats[55] = new Category("Для одежды", cats[52]);
        cats[56] = new Category("Для шеи", cats[52]);
        cats[57] = new Category("Серьги и клипсы", cats[52]);
        cats[58] = new Category("Часы и аксессуары к ним", cats[52]);
        cats[59] = new Category("Другое", cats[52]);
        cats[60] = new Category("Изделия из серебра", cats[43]);
        cats[61] = new Category("Изделия из золота", cats[43]);
        cats[62] = new Category("Другое", cats[43]);


        cats[63] = new Category("Техника и приборы", cats[1]);
        cats[64] = new Category("Патефоны и граммофоны", cats[64]);
        cats[65] = new Category("Печатные машинки", cats[64]);
        cats[66] = new Category("Оптика", cats[64]);
        cats[67] = new Category("Бинокли, подзорыные трубы", cats[66]);
        cats[68] = new Category("Микроскопы, лупы", cats[66]);
        cats[69] = new Category("Оптические приборы", cats[66]);
        cats[70] = new Category("Очки, пенсне", cats[66]);
        cats[71] = new Category("Фото- и конотехника", cats[66]);
        cats[72] = new Category("Другое", cats[66]);
        cats[73] = new Category("Швейные машинки", cats[64]);
        cats[74] = new Category("Компасы, барометры, термометры", cats[64]);
        cats[75] = new Category("Станки", cats[64]);
        cats[76] = new Category("Радиотехника и телефоны", cats[64]);
        cats[77] = new Category("Весы, разновесы, меры", cats[64]);
        cats[78] = new Category("Вычичлительная техника", cats[64]);
        cats[79] = new Category("Инструменты", cats[64]);
        cats[80] = new Category("Чертежные инструменты", cats[64]);
        cats[81] = new Category("Линейки, микрометры", cats[64]);
        cats[82] = new Category("Угломеры, уровни", cats[64]);
        cats[83] = new Category("Манометры, тахометры", cats[64]);
        cats[84] = new Category("Осветительные приборы", cats[64]);
        cats[85] = new Category("Другое", cats[64]);

        cats[86] = new Category("Изделия из фарфора, керамики, фаянса", cats[1]);
        cats[87] = new Category("Статуэтки", cats[86]);
        cats[88] = new Category("Вазы", cats[86]);
        cats[89] = new Category("Посуда", cats[86]);
        cats[90] = new Category("Сервизы", cats[86]);
        cats[91] = new Category("Другое", cats[86]);

        cats[92] = new Category("Текстиль", cats[1]);
        cats[93] = new Category("Одежда", cats[92]);
        cats[94] = new Category("Платки и шали", cats[92]);
        cats[95] = new Category("Ковры", cats[92]);
        cats[96] = new Category("Гобелены", cats[92]);
        cats[97] = new Category("Кружева", cats[92]);
        cats[98] = new Category("Скатерти, ручники, подзоры", cats[92]);
        cats[99] = new Category("Вышивки", cats[92]);
        cats[100] = new Category("Головные уборы", cats[92]);
        cats[101] = new Category("Другое", cats[92]);

        cats[102] = new Category("Предметы декора интерьера", cats[1]);
        cats[103] = new Category("Картины", cats[102]);
        cats[104] = new Category("Мебель", cats[102]);
        cats[105] = new Category("Скульптура", cats[102]);
        cats[106] = new Category("Часы", cats[102]);
        cats[107] = new Category("Освещение", cats[102]);
        cats[108] = new Category("Подсвечники, канделябры, бра", cats[107]);
        cats[109] = new Category("Фонари", cats[107]);
        cats[110] = new Category("Люстры", cats[107]);
        cats[111] = new Category("Настольные и керасиновые лампы", cats[107]);
        cats[112] = new Category("Детали и фрагменты", cats[107]);
        cats[113] = new Category("Плафоны, подвески, абажуры", cats[107]);
        cats[114] = new Category("Другое", cats[107]);
        cats[115] = new Category("Шкатулки", cats[102]);
        cats[116] = new Category("Зеркала", cats[102]);
        cats[117] = new Category("Ширмы", cats[102]);
        cats[118] = new Category("Глобусы", cats[102]);
        cats[119] = new Category("Рамы для картин и фото", cats[102]);
        cats[120] = new Category("Письменные принадлежности", cats[102]);
        cats[121] = new Category("Чугунная пластика", cats[102]);
        cats[122] = new Category("Другое", cats[102]);

        cats[123] = new Category("Кухонная и домашняя утварь", cats[1]);
        cats[124] = new Category("Посуда", cats[123]);
        cats[125] = new Category("Предметы сервировки", cats[123]);
        cats[126] = new Category("Шкатулки", cats[123]);
        cats[127] = new Category("Прялки", cats[123]);
        cats[128] = new Category("Сундуки", cats[123]);
        cats[129] = new Category("Подстаканники", cats[123]);
        cats[130] = new Category("Утюги", cats[123]);
        cats[131] = new Category("Самовары", cats[123]);
        cats[132] = new Category("Замки, ключи, дверные ручки", cats[123]);
        cats[133] = new Category("Каминные принадлежности", cats[123]);
        cats[134] = new Category("Таблички и вывески", cats[123]);
        cats[135] = new Category("Лаковая миниутюра", cats[123]);
        cats[136] = new Category("Ступки", cats[123]);
        cats[137] = new Category("Кашпо", cats[123]);
        cats[138] = new Category("Другое", cats[123]);

        cats[139] = new Category("Мебель", cats[1]);
        cats[140] = new Category("Столы", cats[139]);
        cats[141] = new Category("Стулья", cats[139]);
        cats[142] = new Category("Кровати", cats[139]);
        cats[143] = new Category("Кресла", cats[139]);
        cats[144] = new Category("Шкафы", cats[139]);
        cats[145] = new Category("Гарнитуры", cats[139]);
        cats[146] = new Category("Кушетки", cats[139]);
        cats[147] = new Category("Скамьи", cats[139]);
        cats[148] = new Category("Табуретки", cats[139]);
        cats[149] = new Category("Банкетки", cats[139]);
        cats[150] = new Category("Другое", cats[139]);

        cats[151] = new Category("Скульптура", cats[1]);
        cats[152] = new Category("Станоквая скульптура", cats[151]);
        cats[153] = new Category("Бюст", cats[152]);
        cats[154] = new Category("Голова", cats[152]);
        cats[155] = new Category("Статуя", cats[152]);
        cats[156] = new Category("Торс", cats[152]);
        cats[157] = new Category("Скульптурная группа", cats[152]);
        cats[158] = new Category("Скульптура малых форм", cats[151]);
        cats[159] = new Category("Игрушки", cats[158]);
        cats[160] = new Category("Настольные портретные изоображения", cats[158]);
        cats[161] = new Category("Жанровые статуэтки", cats[158]);
        cats[162] = new Category("Рельеф", cats[151]);
        cats[163] = new Category("Другое", cats[151]);

        cats[164] = new Category("Церковь", cats[1]);
        cats[165] = new Category("Иконы", cats[164]);
        cats[166] = new Category("Лампады", cats[164]);
        cats[167] = new Category("Церковная утварь", cats[164]);
        cats[168] = new Category("Киоты, рамы", cats[164]);
        cats[169] = new Category("Оклады", cats[164]);
        cats[170] = new Category("Другое", cats[164]);

        cats[171] = new Category("Другое", cats[1]);

        for (int i = 1; i <= 171; i++) {
            categoryRepository.save(cats[i]);
        }
    }

    void initProducts() {
        Random random = new Random();
        List<Category> categories = categoryRepository.findAll();
        for (int i = 0; i < 600; i++) {
            Product product = new Product();
            product.setName("Товар " + i);
            product.setStatus(ProductStatus.IN_TRADES);
            product.setAssVal((double) (random.nextInt(4999) + 1) * 100);
            product.setStartPrice((double) (random.nextInt(4999) + 1) * 100);
            product.setCurrPrice((double) (random.nextInt(4999) + 1) * 100);
            product.setStep((double) (random.nextInt(499) + 1) * 100);
            product.setDateBeg(LocalDateTime.now().minusMinutes(random.nextInt(59) + 1));
            product.setDateEnd(LocalDateTime.now().plusMinutes(random.nextInt(59) + 1));
            product.setDescription("Описание товара");
            productRepository.save(product);
            if(i<=103) {
                Image image = new Image(null, product, ".jpg", 0);
                imageRepository.save(image);
            }

            for (int j = 0; j < random.nextInt(3) + 1; j++) {
                Category category = categories.get(random.nextInt(categories.size()));
                if (category == null) {
                    continue;
                }
                if (category.getSubsidiary().size() > 0) {
                    j--;
                    continue;
                }
                CatProd catProd = new CatProd(null, category, product);
                catProdRepository.save(catProd);
            }
        }
    }

    void initAttributes(){
        Attribute attribute1 = new Attribute();
        attribute1.setName("Материал");
        attribute1.setType(AttrType.MULTYPLECHOICE);
        attributeRepository.save(attribute1);
        Attribute attribute2 = new Attribute();
        attribute2.setName("Год создания");
        attribute2.setType(AttrType.INTEGER);
        attributeRepository.save(attribute2);
    }

    void initAttrVals(){
        String[] materials = new String[]{"Золото", "Серебро", "Иридий", "Нефрит", "Ольха", "Берёза", "Дуб", "Кость"};
        Random random = new Random();
        List<Product> products = productRepository.findAll();
        for (Product p: products){
            attributeService.addValue(p.getId(),"Материал", materials[random.nextInt(4)]);
            attributeService.addValue(p.getId(),"Материал", materials[random.nextInt(4)+4]);
            attributeService.addValue(p.getId(), "Год создания", String.valueOf(random.nextInt(300)+1600));
        }
    }

    @Autowired
    public void setUserDescRepository(UserDescRepository userDescRepository) {
        this.userDescRepository = userDescRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Autowired
    public void setCatProdRepository(CatProdRepository catProdRepository) {
        this.catProdRepository = catProdRepository;
    }

    @Autowired
    public void setAttributeRepository(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }

    @Autowired
    public void setAttributeService(AttributeService attributeService) {
        this.attributeService = attributeService;
    }
}
