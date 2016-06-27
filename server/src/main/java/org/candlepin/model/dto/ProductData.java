/**
 * Copyright (c) 2009 - 2016 Red Hat, Inc.
 *
 * This software is licensed to you under the GNU General Public License,
 * version 2 (GPLv2). There is NO WARRANTY for this software, express or
 * implied, including the implied warranties of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. You should have received a copy of GPLv2
 * along with this software; if not, see
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt.
 *
 * Red Hat trademarks are not licensed under GPLv2. No permission is
 * granted to use or replicate Red Hat trademarks that are incorporated
 * in this software or its documentation.
 */
package org.candlepin.model.dto;

import org.candlepin.model.Content;
import org.candlepin.model.Product;
import org.candlepin.model.ProductAttribute;
import org.candlepin.model.ProductContent;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 * DTO representing the product data exposed to the API and adapter layers.
 *
 * <pre>
 * {
 *   "uuid" : "ff808081554a3e4101554a3e9033005d",
 *   "id" : "5051",
 *   "name" : "Admin OS Developer Bits",
 *   "multiplier" : 1,
 *   "attributes" : [ ... ],
 *   "productContent" : [ ... ],
 *   "dependentProductIds" : [ ... ],
 *   "href" : "/products/ff808081554a3e4101554a3e9033005d",
 *   "created" : "2016-06-13T14:51:02+0000",
 *   "updated" : "2016-06-13T14:51:02+0000"
 * }
 * </pre>
 */
@XmlRootElement
public class ProductData extends CandlepinDTO {

    protected String uuid;
    protected String id;
    protected String name;
    protected Long multiplier;
    protected List<ProductAttributeData> attributes;
    protected List<ProductContentData> content;
    protected Set<String> dependentProductIds;
    protected String href;
    protected boolean locked;

    /**
     * Initializes a new ProductData instance with null values.
     */
    public ProductData() {
        super();
    }

    /**
     * Initializes a new ProductData instance using the data contained by the given DTO.
     *
     * @param source
     *  The source DTO from which to copy data
     */
    public ProductData(ProductData source) {
        if (source != null) {
            this.populate(source);
        }
    }

    /**
     * Initializes a new ProductData instance using the data contained by the given entiy.
     *
     * @param entity
     *  The source entity from which to copy data
     */
    public ProductData(Product entity) {
        if (entity != null) {
            this.populate(entity);
        }
    }

    /**
     * Retrieves the UUID of the product represented by this DTO. If the UUID has not yet been
     * defined, this method returns null.
     *
     * @return
     *  the UUID of the product, or null if the UUID has not yet been defined
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Sets the UUID of the product represented by this DTO.
     *
     * @param uuid
     *  The UUID of the product represented by this DTO, or null to clear the UUID
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Retrieves the ID of the product represented by this DTO. If the ID has not yet been
     * defined, this method returns null.
     *
     * @return
     *  the ID of the product, or null if the ID has not yet been defined
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets the ID of the product represented by this DTO.
     *
     * @param id
     *  The ID of the product represented by this DTO
     *
     * @throws IllegalArgumentException
     *  if id is null or empty
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setId(String id) {
        if (id == null || id.length() == 0) {
            throw new IllegalArgumentException("id is null or empty");
        }

        this.id = id;
        return this;
    }

    /**
     * Retrieves the UUID of the product represented by this DTO. If the UUID has not yet been
     * defined, this method returns null.
     *
     * @return
     *  the UUID of the product, or null if the UUID has not yet been defined
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the product represented by this DTO.
     *
     * @param name
     *  The name of the product represented by this DTO, or null to clear the name
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Retrieves the multiplier of the product represented by this DTO. If the multiplier has not
     * yet been defined, this method returns null.
     *
     * @return
     *  the multiplier of the product, or null if the multiplier has not yet been defined
     */
    public Long getMultiplier() {
        return this.multiplier;
    }

    /**
     * Sets the multiplier of the product represented by this DTO.
     *
     * @param multiplier
     *  The multiplier of the product represented by this DTO, or null to clear the multiplier
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setMultiplier(Long multiplier) {
        this.multiplier = multiplier;
        return this;
    }

    /**
     * Retrieves the attributes of the product represented by this DTO. If the product attributes
     * have not yet been defined, this method returns null.
     *
     * @return
     *  the attributes of the product, or null if the attributes have not yet been defined
     */
    public Collection<ProductAttributeData> getAttributes() {
        return this.attributes != null ? Collections.unmodifiableList(this.attributes) : null;
    }

    /**
     * Retrieves the attribute data associated with the given attribute. If the attribute is not
     * set, this method returns null.
     *
     * @param key
     *  The key (name) of the attribute to lookup
     *
     * @throws IllegalArgumentException
     *  if key is null
     *
     * @return
     *  the attribute data for the given attribute, or null if the attribute is not set
     */
    @XmlTransient
    public ProductAttributeData getAttribute(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (this.attributes != null) {
            for (ProductAttributeData attrib : this.attributes) {
                if (key.equals(attrib.getName())) {
                    return attrib;
                }
            }
        }

        return null;
    }

    /**
     * Retrieves the value associated with the given attribute. If the attribute is not set, this
     * method returns null.
     *
     * @param key
     *  The key (name) of the attribute to lookup
     *
     * @throws IllegalArgumentException
     *  if key is null
     *
     * @return
     *  the value set for the given attribute, or null if the attribute is not set
     */
    @XmlTransient
    public String getAttributeValue(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        ProductAttributeData attrib = this.getAttribute(key);
        return attrib != null ? attrib.getValue() : null;
    }

    /**
     * Checks if the given attribute has been defined on this product DTO.
     *
     * @param key
     *  The key (name) of the attribute to lookup
     *
     * @throws IllegalArgumentException
     *  if key is null
     *
     * @return
     *  true if the attribute is defined for this product; false otherwise
     */
    @XmlTransient
    public boolean hasAttribute(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        return this.getAttribute(key) != null;
    }

    /**
     * Adds the specified product attribute DTO to the this product DTO. If the attribute has
     * already been added to this product, the existing value will be overwritten.
     *
     * @param attribute
     *  The product attribute DTO to add to this product DTO
     *
     * @throws IllegalArgumentException
     *  if attribute is null or incomplete
     *
     * @return
     *  true if adding the attribute resulted in a change of this product; false otherwise
     */
    public boolean addAttribute(ProductAttributeData attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("attribute is null");
        }

        if (attribute.getName() == null) {
            throw new IllegalArgumentException("attribute name/key is null");
        }

        boolean changed = false;

        if (this.attributes == null) {
            this.attributes = new LinkedList<ProductAttributeData>();
            changed = this.attributes.add(attribute);
        }
        else {
            // TODO:
            // Replace this with a map of attribute key/value pairs so we don't have this mess
            boolean matched = false;
            Set<ProductAttributeData> remove = new HashSet<ProductAttributeData>();

            for (ProductAttributeData attribdata : this.attributes) {
                if (attribute.getName().equals(attribdata.getName())) {
                    matched = true;

                    if (!(attribdata.getValue() != null ? attribdata.getValue().equals(attribute.getValue()) :
                        attribute.getValue() == null)) {

                        remove.add(attribdata);
                    }
                }
            }

            if (!matched || remove.size() > 0) {
                this.attributes.removeAll(remove);
                changed = this.attributes.add(attribute);
            }
        }

        return changed;
    }

    /**
     * Sets the specified attribute for this product DTO. If the attribute has already been set for
     * this product, the existing value will be overwritten.
     *
     * @param key
     *  The name or key of the attribute to set
     *
     * @param value
     *  The value to assign to the attribute
     *
     * @throws IllegalArgumentException
     *  if key is null
     *
     * @return
     *  true if adding the attribute resulted in a change of this product; false otherwise
     */
    public boolean setAttribute(String key, String value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        return this.addAttribute(new ProductAttributeData(key, value));
    }

    /**
     * Removes the product attribute represented by the given product attribute DTO from this
     * product. Any product attribute with the same key as the key of the given attribute DTO will
     * be removed.
     *
     * @param attribute
     *  The product attribute to remove from this product DTO
     *
     * @throws IllegalArgumentException
     *  if attribute is null or incomplete
     *
     * @return
     *  true if the attribute was removed successfully; false otherwise
     */
    public boolean removeAttribute(ProductAttributeData attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("attribute is null");
        }

        if (attribute.getName() == null) {
            throw new IllegalArgumentException("attribute name is null");
        }

        return this.removeAttribute(attribute.getName());
    }

    /**
     * Removes the product attribute with the given attribute key from this product DTO.
     *
     * @param key
     *  The name/key of the attribute to remove
     *
     * @throws IllegalArgumentException
     *  if key is null
     *
     * @return
     *  true if the attribute was removed successfully; false otherwise
     */
    public boolean removeAttribute(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        boolean changed = false;
        Set<ProductAttributeData> remove = new HashSet<ProductAttributeData>();

        if (this.attributes != null) {
            for (ProductAttributeData attribdata : this.attributes) {
                if (key.equals(attribdata.getName())) {
                    remove.add(attribdata);
                }
            }

            changed = this.attributes.removeAll(remove);
        }

        return changed;
    }

    /**
     * Sets the attributes of the product represented by this DTO.
     *
     * @param attributes
     *  A collection of product attributes DTO to attach to this DTO, or null to clear the
     *  attributes
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setAttributes(Collection<ProductAttributeData> attributes) {
        if (attributes != null) {
            if (this.attributes == null) {
                this.attributes = new LinkedList<ProductAttributeData>();
            }
            else {
                this.attributes.clear();
            }

            for (ProductAttributeData attribute : attributes) {
                this.addAttribute(attribute);
            }
        }
        else {
            this.attributes = null;
        }

        return this;
    }

    /**
     * Retrieves the content of the product represented by this DTO. If the product content has not
     * yet been defined, this method returns null.
     *
     * @return
     *  the content of the product, or null if the content not yet been defined
     */
    public Collection<ProductContentData> getProductContent() {
        return this.content != null ? Collections.unmodifiableCollection(this.content) : null;
    }

    /**
     * Retrieves the product content for the specified content ID. If no such content has been
     * assocaited with this product DTO, this method returns null.
     *
     * @param contentId
     *  The ID of the content to retrieve
     *
     * @throws IllegalArgumentException
     *  if contentId is null
     *
     * @return
     *  the content associated with this DTO using the given ID, or null if such content does not
     *  exist
     */
    public ProductContentData getProductContent(String contentId) {
        if (contentId == null) {
            throw new IllegalArgumentException("contentId is null");
        }

        for (ProductContentData pcd : this.content) {
            if (pcd.getContent() != null && contentId.equals(pcd.getContent().getId())) {
                return pcd;
            }
        }

        return null;
    }

    /**
     * Checks if any content with the given content ID has been associated with this product.
     *
     * @param contentId
     *  The ID of the content to check
     *
     * @throws IllegalArgumentException
     *  if contentId is null
     *
     * @return
     *  true if any content with the given content ID has been associated with this product; false
     *  otherwise
     */
    public boolean hasContent(String contentId) {
        if (contentId == null) {
            throw new IllegalArgumentException("contentId is null");
        }

        return this.getProductContent(contentId) != null;
    }

    /**
     * Adds the given content to this product DTO. If a matching content has already been added to
     * this product, it will be overwritten by the specified content.
     *
     * @param contentData
     *  The product content DTO to add to this product
     *
     * @throws IllegalArgumentException
     *  if content is null or incomplete
     *
     * @return
     *  true if adding the content resulted in a change to this product; false otherwise
     */
    public boolean addProductContent(ProductContentData contentData) {
        if (contentData == null) {
            throw new IllegalArgumentException("contentData is null");
        }

        if (contentData.getContent() == null || contentData.getContent().getId() == null) {
            throw new IllegalArgumentException("content is incomplete");
        }

        boolean changed = false;

        if (this.content == null) {
            this.content = new LinkedList<ProductContentData>();
            changed = this.content.add(contentData);
        }
        else {
            boolean matched = false;
            Collection<ProductContentData> remove = new LinkedList<ProductContentData>();

            // We're operating under the assumption that we won't be doing janky things like
            // adding product content, then changing it. It's too bad this isn't all immutable...
            for (ProductContentData pcd : this.content) {
                ContentData cd = pcd.getContent();

                if (cd != null && cd.getId() != null && cd.getId().equals(contentData.getContent().getId())) {
                    matched = true;

                    if (!pcd.equals(contentData)) {
                        remove.add(pcd);
                    }
                }
            }

            if (!matched || remove.size() > 0) {
                this.content.removeAll(remove);
                changed = this.content.add(contentData);
            }
        }

        return changed;
    }

    /**
     * Adds the given content to this product DTO. If a matching content has already been added to
     * this product, it will be overwritten by the specified content.
     *
     * @param productContent
     *  The product content DTO to add to this product
     *
     * @throws IllegalArgumentException
     *  if productContent is null
     *
     * @return
     *  true if adding the content resulted in a change to this product; false otherwise
     */
    public boolean addProductContent(ProductContent productContent) {
        if (productContent == null) {
            throw new IllegalArgumentException("productContent is null");
        }

        return this.addProductContent(productContent.toDTO());
    }

    /**
     * Adds the given content to this product DTO. If a matching content has already been added to
     * this product, it will be overwritten by the specified content.
     *
     * @param content
     *  The product content DTO to add to this product
     *
     * @throws IllegalArgumentException
     *  if content is null
     *
     * @return
     *  true if adding the content resulted in a change to this product; false otherwise
     */
    public boolean addContent(ContentData content, boolean enabled) {
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }

        return this.addProductContent(new ProductContentData(content, enabled));
    }

    /**
     * Adds the given content to this product DTO. If a matching content has already been added to
     * this product, it will be overwritten by the specified content.
     *
     * @param content
     *  The product content DTO to add to this product
     *
     * @throws IllegalArgumentException
     *  if content is null
     *
     * @return
     *  true if adding the content resulted in a change to this product; false otherwise
     */
    public boolean addContent(Content content, boolean enabled) {
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }

        return this.addProductContent(new ProductContentData(content.toDTO(), enabled));
    }

    /**
     * Removes the content with the given content ID from this product DTO.
     *
     * @param contentId
     *  The ID of the content to remove
     *
     * @throws IllegalArgumentException
     *  if contentId is null
     *
     * @return
     *  true if the content was removed successfully; false otherwise
     */
    public boolean removeContent(String contentId) {
        if (contentId == null) {
            throw new IllegalArgumentException("contentId is null");
        }

        boolean updated = false;

        if (this.content != null) {
            Collection<ProductContentData> remove = new LinkedList<ProductContentData>();

            for (ProductContentData pcd : this.content) {
                ContentData cd = pcd.getContent();

                if (cd != null && contentId.equals(cd.getId())) {
                    remove.add(pcd);
                }
            }

            updated = this.content.removeAll(remove);
        }

        return updated;
    }

    /**
     * Removes the content represented by the given content entity from this product. Any content
     * with the same ID as the ID of the given content entity will be removed.
     *
     * @param content
     *  The content entity representing the content to remove from this product
     *
     * @throws IllegalArgumentException
     *  if content is null or incomplete
     *
     * @return
     *  true if the content was removed successfully; false otherwise
     */
    public boolean removeContent(Content content) {
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }

        if (content.getId() == null) {
            throw new IllegalArgumentException("content is incomplete");
        }

        return this.removeContent(content.getId());
    }

    /**
     * Removes the content represented by the given content DTO from this product. Any content with
     * the same ID as the ID of the given content DTO will be removed.
     *
     * @param content
     *  The product content DTO representing the content to remove from this product
     *
     * @throws IllegalArgumentException
     *  if content is null or incomplete
     *
     * @return
     *  true if the content was removed successfully; false otherwise
     */
    public boolean removeContent(ContentData content) {
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }

        if (content.getId() == null) {
            throw new IllegalArgumentException("content is incomplete");
        }

        return this.removeContent(content.getId());
    }

    /**
     * Removes the content represented by the given content entity from this product. Any content
     * with the same ID as the ID of the given content entity will be removed.
     *
     * @param content
     *  The product content entity representing the content to remove from this product
     *
     * @throws IllegalArgumentException
     *  if content is null or incomplete
     *
     * @return
     *  true if the content was removed successfully; false otherwise
     */
    public boolean removeProductContent(ProductContent content) {
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }

        if (content.getContent() == null || content.getContent().getId() == null) {
            throw new IllegalArgumentException("content is incomplete");
        }

        return this.removeContent(content.getContent().getId());
    }

    /**
     * Removes the content represented by the given content DTO from this product. Any content with
     * the same ID as the ID of the given content DTO will be removed.
     *
     * @param content
     *  The product content DTO representing the content to remove from this product
     *
     * @throws IllegalArgumentException
     *  if content is null or incomplete
     *
     * @return
     *  true if the content was removed successfully; false otherwise
     */
    public boolean removeProductContent(ProductContentData content) {
        if (content == null) {
            throw new IllegalArgumentException("content is null");
        }

        if (content.getContent() == null || content.getContent().getId() == null) {
            throw new IllegalArgumentException("content is incomplete");
        }

        return this.removeContent(content.getContent().getId());
    }

    /**
     * Sets the content of the product represented by this DTO.
     *
     * @param content
     *  A collection of product content DTO to attach to this DTO, or null to clear the content
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setProductContent(Collection<ProductContentData> content) {
        if (content != null) {
            if (this.content == null) {
                this.content = new LinkedList<ProductContentData>();
            }
            else {
                this.content.clear();
            }

            for (ProductContentData pcd : content) {
                this.addProductContent(pcd);
            }
        }
        else {
            this.content = null;
        }

        return this;
    }

    /**
     * Retrieves the dependent product IDs of the product represented by this DTO. If the product
     * dependent product IDs have not yet been defined, this method returns null.
     *
     * @return
     *  the dependent product IDs of the product, or null if the dependent product IDs have not yet
     *  been defined
     */
    public Collection<String> getDependentProductIds() {
        return this.dependentProductIds != null ?
            Collections.unmodifiableSet(this.dependentProductIds) :
            null;
    }

    /**
     * Adds the ID of the specified product as a dependent product of this product. If the product
     * is already a dependent product, it will not be added again.
     *
     * @param productId
     *  The ID of the product to add as a dependent product
     *
     * @throws IllegalArgumentException
     *  if productId is null
     *
     * @return
     *  true if the dependent product was added successfully; false otherwise
     */
    public boolean addDependentProductId(String productId) {
        if (productId == null) {
            throw new IllegalArgumentException("productId is null");
        }

        if (this.dependentProductIds == null) {
            this.dependentProductIds = new HashSet<String>();
        }

        return this.dependentProductIds.add(productId);
    }

    /**
     * Removes the specified product as a dependent product of this product. If the product is not
     * dependent on this product, this method does nothing.
     *
     * @param productId
     *  The ID of the product to add as a dependent product
     *
     * @throws IllegalArgumentException
     *  if productId is null
     *
     * @return
     *  true if the dependent product was removed successfully; false otherwise
     */
    public boolean removeDependentProductId(String productId) {
        return this.dependentProductIds != null ? this.dependentProductIds.remove(productId) : false;
    }

    /**
     * Sets the dependent product IDs of the product represented by this DTO.
     *
     * @param dependentProductIds
     *  A collection of dependent product IDs to attach to this DTO, or null to clear the
     *  dependent products
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setDependentProductIds(Collection<String> dependentProductIds) {
        if (dependentProductIds != null) {
            if (this.dependentProductIds == null) {
                this.dependentProductIds = new HashSet<String>();
            }
            else {
                this.dependentProductIds.clear();
            }

            for (String pid : dependentProductIds) {
                this.addDependentProductId(pid);
            }
        }
        else {
            this.dependentProductIds = null;
        }

        return this;
    }

    /**
     * Retrieves the link of the product represented by this DTO. If the product hyperlink has not
     * yet been defined, this method returns null.
     *
     * @return
     *  the link of the product, or null if the link have not yet been defined
     */
    public String getHref() {
        return this.href;
    }

    /**
     * Sets the hyperlink of the product represented by this DTO.
     *
     * @param href
     *  The hyperlink of the product represented by this DTO, or null to clear the hyperlink
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setHref(String href) {
        this.href = href;
        return this;
    }

    /**
     * Retrieves the lock state of the product represented by this DTO. If the lock state has not
     * yet been defined, this method returns null.
     *
     * @return
     *  the lock state of the product, or null if the lock state has not yet been defined
     */
    @XmlTransient
    public Boolean isLocked() {
        return this.locked;
    }

    /**
     * Sets the lock state of the product represented by this DTO.
     *
     * @param locked
     *  The lock state of the product represented by this DTO, or null to clear the state
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    @Override
    public String toString() {
        return String.format("ProductData [id = %s, name = %s]", this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ProductData)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        ProductData that = (ProductData) obj;

        EqualsBuilder builder = new EqualsBuilder()
            .append(this.id, that.id)
            .append(this.name, that.name)
            .append(this.multiplier, that.multiplier)
            .append(this.attributes, that.attributes)
            .append(this.href, that.href)
            .append(this.content, that.content)
            .append(this.dependentProductIds, that.dependentProductIds);

        return super.equals(obj) && builder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder(37, 7)
            .append(super.hashCode())
            .append(this.id)
            .append(this.name)
            .append(this.multiplier)
            .append(this.href)
            .append(this.attributes)
            .append(this.content)
            .append(this.dependentProductIds);

        return builder.toHashCode();
    }

    @Override
    public Object clone() {
        ProductData copy = (ProductData) super.clone();

        if (this.attributes != null) {
            copy.attributes = new LinkedList<ProductAttributeData>();

            for (ProductAttributeData pad : this.attributes) {
                copy.attributes.add((ProductAttributeData) pad.clone());
            }
        }

        if (this.content != null) {
            copy.content = new LinkedList<ProductContentData>();

            for (ProductContentData pac : this.content) {
                copy.content.add((ProductContentData) pac.clone());
            }
        }

        if (this.dependentProductIds != null) {
            copy.dependentProductIds = new HashSet<String>();
            copy.dependentProductIds.addAll(this.dependentProductIds);
        }

        return copy;
    }

    /**
     * Populates this DTO with the data from the given source DTO.
     *
     * @param source
     *  The source DTO from which to copy data
     *
     * @throws IllegalArgumentException
     *  if source is null
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData populate(ProductData source) {
        if (source == null) {
            throw new IllegalArgumentException("source is null");
        }

        super.populate(source);

        this.setUuid(source.getUuid());
        this.setId(source.getId());
        this.setName(source.getName());
        this.setMultiplier(source.getMultiplier());
        this.setHref(source.getHref());
        this.setLocked(source.isLocked());
        this.setAttributes(source.getAttributes());
        this.setProductContent(source.getProductContent());
        this.setDependentProductIds(source.getDependentProductIds());

        return this;
    }

    /**
     * Populates this DTO with data from the given source entity.
     *
     * @param source
     *  The source entity from which to copy data
     *
     * @throws IllegalArgumentException
     *  if source is null
     *
     * @return
     *  a reference to this DTO
     */
    public ProductData populate(Product source) {
        if (source == null) {
            throw new IllegalArgumentException("source is null");
        }

        super.populate(source);

        this.setUuid(source.getUuid());
        this.setId(source.getId());
        this.setName(source.getName());
        this.setMultiplier(source.getMultiplier());
        this.setHref(source.getHref());
        this.setLocked(source.isLocked());

        if (source.getAttributes() != null) {
            if (this.attributes == null) {
                this.attributes = new LinkedList<ProductAttributeData>();
            }
            else {
                this.attributes.clear();
            }

            for (ProductAttribute entity : source.getAttributes()) {
                this.addAttribute(entity.toDTO());
            }
        }

        if (source.getProductContent() != null) {
            if (this.content == null) {
                this.content = new LinkedList<ProductContentData>();
            }
            else {
                this.content.clear();
            }

            for (ProductContent entity : source.getProductContent()) {
                this.addProductContent(entity.toDTO());
            }
        }

        this.setDependentProductIds(source.getDependentProductIds());

        return this;
    }
}
