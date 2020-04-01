package fr.eni.appliTrocEnchere.dal;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public abstract interface ArticleVenduDAO {
	
	public List<ArticleVendu> selectArticleVendu() throws BusinessException;
//	public void addArticle(ArticleVendu article) throws BusinessException;
	public void addArticle(Retrait retrait) throws BusinessException;
	public void updateArticle(ArticleVendu article) throws BusinessException;
	public void deleteArticle(ArticleVendu article) throws BusinessException;
	//public ArticleVendu selectArticleById(int noArticle) throws BusinessException;
	public Retrait selectArticleById(int noArticle) throws BusinessException;
	public void updatePrixDeVente (int proposition,int noArticle) throws BusinessException;
}
