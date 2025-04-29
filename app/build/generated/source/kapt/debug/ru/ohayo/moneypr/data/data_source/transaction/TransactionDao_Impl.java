package ru.ohayo.moneypr.data.data_source.transaction;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import ru.ohayo.moneypr.domain.TransactionEntity;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionEntity> __insertionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __updateAdapterOfTransactionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTransaction;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntity = new EntityInsertionAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`amount`,`content`,`timestamp`,`category`,`fromAccount`,`toAccount`,`currency`,`id`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindDouble(1, entity.getAmount());
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        statement.bindLong(3, entity.getTimestamp());
        statement.bindLong(4, entity.getCategory());
        if (entity.getFromAccount() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getFromAccount());
        }
        if (entity.getToAccount() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getToAccount());
        }
        if (entity.getCurrency() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getCurrency());
        }
        if (entity.getId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getId());
        }
      }
    };
    this.__updateAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `amount` = ?,`content` = ?,`timestamp` = ?,`category` = ?,`fromAccount` = ?,`toAccount` = ?,`currency` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindDouble(1, entity.getAmount());
        if (entity.getContent() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getContent());
        }
        statement.bindLong(3, entity.getTimestamp());
        statement.bindLong(4, entity.getCategory());
        if (entity.getFromAccount() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getFromAccount());
        }
        if (entity.getToAccount() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getToAccount());
        }
        if (entity.getCurrency() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getCurrency());
        }
        if (entity.getId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getId());
        }
        if (entity.getId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteTransaction = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTransaction(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransactionEntity.insert(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTransaction(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransactionEntity.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTransaction(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTransaction.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteTransaction.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TransactionEntity>> getAllTransactions() {
    final String _sql = "SELECT * FROM transactions";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfFromAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "fromAccount");
          final int _cursorIndexOfToAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "toAccount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpCategory;
            _tmpCategory = _cursor.getLong(_cursorIndexOfCategory);
            final Long _tmpFromAccount;
            if (_cursor.isNull(_cursorIndexOfFromAccount)) {
              _tmpFromAccount = null;
            } else {
              _tmpFromAccount = _cursor.getLong(_cursorIndexOfFromAccount);
            }
            final Long _tmpToAccount;
            if (_cursor.isNull(_cursorIndexOfToAccount)) {
              _tmpToAccount = null;
            } else {
              _tmpToAccount = _cursor.getLong(_cursorIndexOfToAccount);
            }
            final Long _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getLong(_cursorIndexOfCurrency);
            }
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _item = new TransactionEntity(_tmpAmount,_tmpContent,_tmpTimestamp,_tmpCategory,_tmpFromAccount,_tmpToAccount,_tmpCurrency,_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTransactionById(final long id,
      final Continuation<? super TransactionEntity> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransactionEntity>() {
      @Override
      @Nullable
      public TransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfFromAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "fromAccount");
          final int _cursorIndexOfToAccount = CursorUtil.getColumnIndexOrThrow(_cursor, "toAccount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final TransactionEntity _result;
          if (_cursor.moveToFirst()) {
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpCategory;
            _tmpCategory = _cursor.getLong(_cursorIndexOfCategory);
            final Long _tmpFromAccount;
            if (_cursor.isNull(_cursorIndexOfFromAccount)) {
              _tmpFromAccount = null;
            } else {
              _tmpFromAccount = _cursor.getLong(_cursorIndexOfFromAccount);
            }
            final Long _tmpToAccount;
            if (_cursor.isNull(_cursorIndexOfToAccount)) {
              _tmpToAccount = null;
            } else {
              _tmpToAccount = _cursor.getLong(_cursorIndexOfToAccount);
            }
            final Long _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getLong(_cursorIndexOfCurrency);
            }
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _result = new TransactionEntity(_tmpAmount,_tmpContent,_tmpTimestamp,_tmpCategory,_tmpFromAccount,_tmpToAccount,_tmpCurrency,_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
