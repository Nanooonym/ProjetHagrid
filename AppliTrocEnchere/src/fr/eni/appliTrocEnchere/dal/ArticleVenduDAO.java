package fr.eni.appliTrocEnchere.dal;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public abstract interface ArticleVenduDAO {
	
	List<ArticleVendu> selectArticleVendu() throws BusinessException;
}
